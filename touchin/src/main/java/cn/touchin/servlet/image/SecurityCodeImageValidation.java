package cn.touchin.servlet.image;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;

public abstract class SecurityCodeImageValidation extends HttpServlet {
    private static final long serialVersionUID = -3949317603463375225L;

    protected Log log = Logs.getLog(getClass());

    public static final String SECURITY_CODE_KEY = "_S_C_KEY_";
    private int securityCodeLength = 4;
    private int imageWidth = 80;
    private int imageHight = 30;

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext app = config.getServletContext();
        String scLen = app.getInitParameter("codelength");
        String iWhdth = app.getInitParameter("imageWidth");
        String iHight = app.getInitParameter("imageHight");
        try {
            securityCodeLength = Strings.isBlank(scLen) ? 4 : Integer.parseInt(scLen);
            imageWidth = Strings.isBlank(iWhdth) ? 80 : Integer.parseInt(iWhdth);
            imageHight = Strings.isBlank(iHight) ? 30 : Integer.parseInt(iHight);
        } catch (Throwable e) {
            log.warn(e.getMessage(), e);
        }
    }

    /**
     * 
     * @return Security Code length
     */
    protected int definedSecurityCodeLength() {
        return securityCodeLength;
    }

    /**
     * 
     * @return image width
     */
    protected int definedImageWidth() {
        return imageWidth;
    }

    /**
     * 
     * @return image hight
     */
    protected int definedImageHight() {
        return imageHight;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest ,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setDateHeader("Expires", -1);

        String code = generateSecurityCode();
        if (code != null && code.length() == definedSecurityCodeLength()) {
            sendSecurityCode(request, response, code);
            bulidSecurityCodeImage(response, code);
        }
    }

    private void bulidSecurityCodeImage(HttpServletResponse response, String code) {
        Random random = new Random();

        BufferedImage image = new BufferedImage(definedImageWidth(), definedImageHight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, definedImageWidth(), definedImageHight());
        g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        // 随机产生干扰线底线
        for (int i = 0; i < 4; i++) {
            int x = random.nextInt(definedImageWidth());
            int y = random.nextInt(definedImageHight());
            int xl = random.nextInt(15);
            int yl = random.nextInt(15);
            g.setColor(getRandColor(10, 130));
            g.drawLine(x, y, x + xl, y + yl);
        }
        // 随机产生干扰线
        for (int i = 0; i < 4; i++) {
            g.setColor(getRandColor(20, 140));
            g.drawLine(random.nextInt(definedImageWidth()), random.nextInt(definedImageHight()),
                    random.nextInt(definedImageWidth()), random.nextInt(definedImageHight()));
        }

        double oldrot = 0;
        for (int i = 0; i < definedSecurityCodeLength(); i++) {
            String vCode = String.valueOf(code.charAt(i));
            g.setColor(getRandColor(20, 140));

            double rot = (random.nextInt(60) - 30) * Math.PI / 180;
            g.rotate(-oldrot, oldrot == 0 ? 10 : (15 * (i - 1) + 10), 15);
            oldrot = rot;
            g.rotate(rot, 15 * i + 10, 15);
            float stroke = Math.abs(random.nextFloat() % 30);
            g.setStroke(new BasicStroke(stroke));

            g.drawString(vCode, 15 * i + 10, 20);
        }

        g.dispose();
        try {
            ImageIO.write(image, "JPEG", response.getOutputStream());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    /**
     * 生成验证码，默认字母数字混合
     * 
     * @return securityCode
     */
    protected String generateSecurityCode() {
        return SecurityCodeRandom.getRandomID(definedSecurityCodeLength());
    }

    /**
     * 默认验证码保存到session中
     * 
     * @param request
     * @param response
     * @param code
     */
    protected void sendSecurityCode(HttpServletRequest request, HttpServletResponse response, String code) {
        HttpSession session = request.getSession();
        if (session != null) {
            session.setAttribute(SECURITY_CODE_KEY, code);
        }
    }

    /**
     * 获得之前的code
     * 
     * @param request
     * @param response
     * @return
     */
    public abstract String getOldSecurityCode(HttpServletRequest request, HttpServletResponse response);

    /**
     * 从session中获得之前的code
     * 
     * @param request
     * @param response
     * @return OldSecurityCode
     */
    public String getOldSecurityCodeFromSession(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session != null) {
            return (String) session.getAttribute(SECURITY_CODE_KEY);
        }
        return null;
    }

    /**
     * 从cookies中获得之前的code
     * 
     * @param request
     * @param response
     * @return OldSecurityCode
     */
    public String getOldSecurityCodeFromCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(SECURITY_CODE_KEY)) {
                    String old = cookies[i].getValue();
                    if (Strings.isBlank(old)) {
                        continue;
                    }
                    return old;
                }
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest ,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.GenericServlet#destroy()
     */
    @Override
    public void destroy() {
        super.destroy();
    }

}
