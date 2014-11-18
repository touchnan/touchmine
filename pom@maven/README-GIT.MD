https://help.github.com/articles/create-a-repo
https://help.github.com/articles/fork-a-repo
https://help.github.com/articles/set-up-git

Create a new repository on the command line

touch README.md
git init
git add README.md
git commit -m "first commit"
git remote add origin https://github.com/touchnan/touchool.git
git push -u origin master

Push an existing repository from the command line

git remote add origin https://github.com/touchnan/touchool.git
(git remote add origin https://github.com/touchnan/touchmine.git)
git push -u origin master



Set Up Git(https://help.github.com/articles/set-up-git)

git config --global user.name "touchnan"
git config --global user.email "88052350@qq.com"

git remote rm origin


假设你fork的项目原始地址是http://github.com/abc/rep.git, 你自己的是http://github.com/you/rep.git

$ git remote  add upstream http://github.com/abc/rep.git # 你本地的origin应该跟了自己的remote，前且假设当前本地branch是master。
$ git fetch upstream
$ git merge upstream/master  #merge可能会有冲突，手工解决掉并commit
$ git push # push到你自己的fork上

然后向原始项目提交一个pull request。 不知道你是不是想要这个...我现在是通过这种方式更新的，直接用git 的 bash 