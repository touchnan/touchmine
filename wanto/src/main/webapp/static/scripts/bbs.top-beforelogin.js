document.writeln("<div class=\"bbs-topmenu\">");
document.writeln("		<div class=\"bbs-topmenu-main\">");
document.writeln("			<div style=\"z-index: 20; position: relative;\">");
document.writeln("				<h1>1+1=2</h1>");
document.writeln("<ul class=\"menu1\">");
document.writeln("					<li class=\"menu1-li active\">");
document.writeln("						<a href=\"index.html\"><span>首页</span></a>");
document.writeln("					</li>");
document.writeln("					<li class=\"menu1-li\">");
document.writeln("						<a class=\"dianpu\" href=\"bbs.shop-index.html\"><span>店铺</span></a>");
document.writeln("					</li>");
document.writeln("					<li class=\"menu1-li\">");
document.writeln("						<a href=\"bbs.circle-index.html\"><span>圈子(近期开启)</span></a>");
document.writeln("					</li>");
document.writeln("					<li class=\"menu1-li\">");
document.writeln("						<a href=\"javascript:;\"><span>活动(近期开启)</span></a>");
document.writeln("					</li>");

// 搜索框
document.writeln("<li class=\"bbs-searchbar\">");
document.writeln("		<!-- <select>");
document.writeln("			<option value=\"1\">店铺</option>");
document.writeln("		</select> -->");
document.writeln("		<input type=\"text\" value=\"\"/>");
document.writeln("		<a style=\"float: right;\" target=\"_blank\" href=\"bbs.list-shop.html\">");
document.writeln("			<img style=\"width:20px;\" src=\"../images/search.png\"></img>");
document.writeln("		</a>");
document.writeln("		<!-- <button class=\"all-btn blue-btn\">搜索</button> -->");
document.writeln("	</li>");

// 注册登录按钮
document.writeln("                  <li class=\"menu1-li menu1-li-right\">");
document.writeln("					    <a class=\"reg-link\" href=\"javascript:;\">");
document.writeln("							<span>注册/登录</span>");
document.writeln("					    </a>");
document.writeln("				    </li>");


document.writeln("				</ul>");
document.writeln("			</div>");
document.writeln("		</div>");
document.writeln("	</div>");


// 登录弹出框
document.writeln("<div id=\"inner-reg\" class=\"inner-reg-box\" style=\"display:none;\">");
document.writeln("			<h2>登 录<span><a href=\"bbs.register.html\">我要注册！</a></span></h2>");
document.writeln("			<form>");
document.writeln("				<ul class=\"innerlogin-list\">");
document.writeln("					<li>");
document.writeln("						<label>");
document.writeln("							<span class=\"list-l\">");
document.writeln("								Email：");
document.writeln("							</span>");
document.writeln("							<input type=\"text\" style=\"width:60%;\">");
document.writeln("						</label>");
document.writeln("					</li>");
document.writeln("					<li>");
document.writeln("						<label>");
document.writeln("							<span class=\"list-l\">");
document.writeln("								密 码：");
document.writeln("							</span>");
document.writeln("							<input type=\"password\" style=\"width:60%;\">");
document.writeln("						</label>");
document.writeln("					</li>");

document.writeln("					<li>");
document.writeln("						<div class=\"list-msg\">");
document.writeln("							<a href=\"bbs.user-account.html\" class=\"all-btn green-btn\">登 录</a>");
document.writeln("							<label style=\"margin-left: 32px;\">");
document.writeln("								<input type=\"checkbox\">记住我");
document.writeln("		                	</label>");
document.writeln("							<p class=\"list-msg-error\">用户名密码不匹配，请重新输入</p>");
document.writeln("						</div>");
document.writeln("					</li>");

document.writeln("					<li>");
document.writeln("						<a href=\"#\">忘记密码 &#10132 </a>");
document.writeln("					</li>");
document.writeln("				</ul>");
document.writeln("			</form>");
document.writeln("			<a id=\"inner-close\" class=\"inner-close-btn\" title=\"关闭\" href=\"javascript:;\">关闭</a>");
document.writeln("		</div> ");

