#Cloning submodules

#Init all submodules
git submodule init
git submodule update

#Cloning each submodule
git checkout master
git merge HEAD@{1}
git push origin master
