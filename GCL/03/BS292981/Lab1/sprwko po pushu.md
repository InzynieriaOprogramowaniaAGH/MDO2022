bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1$ sudo GIT_SSH_COMMAND="ssh -i ~/.ssh/id-ed25519" git clone git@github.com:InzynieriaOprogramowaniaAGH/MDO2022.git
[sudo] password for bartek:
Sorry, try again.
[sudo] password for bartek:
Cloning into 'MDO2022'...
Warning: Identity file /root/.ssh/id-ed25519 not accessible: No such file or directory.
The authenticity of host 'github.com (140.82.121.3)' can't be established.
ECDSA key fingerprint is SHA256:p2QAMXNIC1TJYWeIOttrVc98/R1BUFWu3/LiyKgUfQM.
Are you sure you want to continue connecting (yes/no/[fingerprint])? ^Z
[1]+  Stopped                 sudo GIT_SSH_COMMAND="ssh -i ~/.ssh/id-ed25519" git clone git@github.com:InzynieriaOprogramowaniaAGH/MDO2022.git
bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1$ sudo GIT_SSH_COMMAND="ssh -i ~/.ssh/id-ed25519" git clone git@github.com:InzynieriaOprogramowaniaAGH/MDO2022.git
fatal: destination path 'MDO2022' already exists and is not an empty directory.
bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1$ sudo GIT_SSH_COMMAND="ssh -i ~/.ssh/id-ed25519" git clone git@github.com:InzynieriaOprogramowaniaAGH/MDO2022.git
Cloning into 'MDO2022'...
Warning: Identity file /root/.ssh/id-ed25519 not accessible: No such file or directory.
The authenticity of host 'github.com (140.82.121.3)' can't be established.
ECDSA key fingerprint is SHA256:p2QAMXNIC1TJYWeIOttrVc98/R1BUFWu3/LiyKgUfQM.
Are you sure you want to continue connecting (yes/no/[fingerprint])? ^C
bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1$ GIT_SSH_COMMAND="ssh -i ~/.ssh/id-ed25519" git clone git@github.com:InzynieriaOprogramowaniaAGH/MDO2022.git
Cloning into 'MDO2022'...
Warning: Identity file /home/bartek/.ssh/id-ed25519 not accessible: No such file or directory.
remote: Enumerating objects: 7885, done.
remote: Counting objects: 100% (2959/2959), done.
^Cfatal: the remote end hung up unexpectedly

bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1$ git clone git@github.com:InzynieriaOprogramowaniaAGH/MDO2022.git
Cloning into 'MDO2022'...
remote: Enumerating objects: 7885, done.
remote: Counting objects: 100% (2959/2959), done.
remote: Compressing objects: 100% (2172/2172), done.
remote: Total 7885 (delta 735), reused 2094 (delta 421), pack-reused 4926
Receiving objects: 100% (7885/7885), 252.47 MiB | 765.00 KiB/s, done.
Resolving deltas: 100% (1475/1475), done.
bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1$ cd MDO2022/
bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1/MDO2022$ git checkout GCL03
Updating files: 100% (686/686), done.
Branch 'GCL03' set up to track remote branch 'GCL03' from 'origin'.
Switched to a new branch 'GCL03'
bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1/MDO2022$ cd GCL/03
bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1/MDO2022/GCL/03$ ls
AT285937  BU279043  DZ307697  FZ307698  MM301180  MR299810  NS306496  SS306505
bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1/MDO2022/GCL/03$ mkdir BS292981
bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1/MDO2022/GCL/03$ cd BS292981/
bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1/MDO2022/GCL/03/BS292981$ git add .
bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1/MDO2022/GCL/03/BS292981$ git status
On branch GCL03
Your branch is up to date with 'origin/GCL03'.

Changes to be committed:
  (use "git restore --staged <file>..." to unstage)
        new file:   sprawozdanie1DEV.txt

bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1/MDO2022/GCL/03/BS292981$ git commit -m Lab
[GCL03 04eae7f] Lab
 1 file changed, 32 insertions(+)
 create mode 100644 GCL/03/BS292981/sprawozdanie1DEV.txt
bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1/MDO2022/GCL/03/BS292981$ git push --set-upstream origin BS292981
error: src refspec BS292981 does not match any
error: failed to push some refs to 'git@github.com:InzynieriaOprogramowaniaAGH/MDO2022.git'
bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1/MDO2022/GCL/03/BS292981$ cd..
cd..: command not found
bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1/MDO2022/GCL/03/BS292981$ cd ..
bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1/MDO2022/GCL/03$ cd ..
bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1/MDO2022/GCL$ cd ..
bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1/MDO2022$ git checkout -b BS292981
Switched to a new branch 'BS292981'
bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1/MDO2022$ cd GCL/03/BS292981
bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1/MDO2022/GCL/03/BS292981$ git add .
bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1/MDO2022/GCL/03/BS292981$ git status
On branch BS292981
nothing to commit, working tree clean
bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1/MDO2022/GCL/03/BS292981$ git status
On branch BS292981
nothing to commit, working tree clean
bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1/MDO2022/GCL/03/BS292981$ git status
On branch BS292981
Changes not staged for commit:
  (use "git add/rm <file>..." to update what will be committed)
  (use "git restore <file>..." to discard changes in working directory)
        deleted:    sprawozdanie1DEV.txt

no changes added to commit (use "git add" and/or "git commit -a")
bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1/MDO2022/GCL/03/BS292981$ git status
On branch BS292981
Changes not staged for commit:
  (use "git add/rm <file>..." to update what will be committed)
  (use "git restore <file>..." to discard changes in working directory)
        deleted:    sprawozdanie1DEV.txt

Untracked files:
  (use "git add <file>..." to include in what will be committed)
        sprawozdanie1.txt

no changes added to commit (use "git add" and/or "git commit -a")
bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1/MDO2022/GCL/03/BS292981$ git add .
bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1/MDO2022/GCL/03/BS292981$ git status
On branch BS292981
Changes to be committed:
  (use "git restore --staged <file>..." to unstage)
        renamed:    sprawozdanie1DEV.txt -> sprawozdanie1.txt

bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1/MDO2022/GCL/03/BS292981$ git commit -m Lab1
[BS292981 55cda34] Lab1
 1 file changed, 0 insertions(+), 0 deletions(-)
 rename GCL/03/BS292981/{sprawozdanie1DEV.txt => sprawozdanie1.txt} (100%)
bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1/MDO2022/GCL/03/BS292981$ git push --set-upstream origin BS292981
Enumerating objects: 14, done.
Counting objects: 100% (14/14), done.
Delta compression using up to 8 threads
Compressing objects: 100% (9/9), done.
Writing objects: 100% (11/11), 1.52 KiB | 57.00 KiB/s, done.
Total 11 (delta 6), reused 0 (delta 0)
remote: Resolving deltas: 100% (6/6), completed with 3 local objects.
remote:
remote: Create a pull request for 'BS292981' on GitHub by visiting:
remote:      https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/pull/new/BS292981
remote:
To github.com:InzynieriaOprogramowaniaAGH/MDO2022.git
 * [new branch]      BS292981 -> BS292981
Branch 'BS292981' set up to track remote branch 'BS292981' from 'origin'.
bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1/MDO2022/GCL/03/BS292981$ git add .
bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1/MDO2022/GCL/03/BS292981$ git status
On branch BS292981
Your branch is up to date with 'origin/BS292981'.

Changes to be committed:
  (use "git restore --staged <file>..." to unstage)
        renamed:    sprawozdanie1.txt -> Lab1/sprawozdanie1.txt

bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1/MDO2022/GCL/03/BS292981$ cd ..
bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1/MDO2022/GCL/03$ cd BS292981/
bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1/MDO2022/GCL/03/BS292981$ ls
Lab1  Lab2  Lab3
bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1/MDO2022/GCL/03/BS292981$ git add .
bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1/MDO2022/GCL/03/BS292981$ git status
On branch BS292981
Your branch is up to date with 'origin/BS292981'.

Changes to be committed:
  (use "git restore --staged <file>..." to unstage)
        renamed:    sprawozdanie1.txt -> Lab1/sprawozdanie1.txt

bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1/MDO2022/GCL/03/BS292981$ git commit -m "aktualizacja sprawka i foldery"
[BS292981 1decb34] aktualizacja sprawka i foldery
 1 file changed, 0 insertions(+), 0 deletions(-)
 rename GCL/03/BS292981/{ => Lab1}/sprawozdanie1.txt (100%)
bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1/MDO2022/GCL/03/BS292981$ git push
Enumerating objects: 9, done.
Counting objects: 100% (9/9), done.
Delta compression using up to 8 threads
Compressing objects: 100% (4/4), done.
Writing objects: 100% (5/5), 439 bytes | 31.00 KiB/s, done.
Total 5 (delta 3), reused 0 (delta 0)
remote: Resolving deltas: 100% (3/3), completed with 3 local objects.
To github.com:InzynieriaOprogramowaniaAGH/MDO2022.git
   55cda34..1decb34  BS292981 -> BS292981
bartek@DESKTOP-4TRDJM6:/mnt/c/Users/48889/Desktop/DEVOPS1/MDO2022/GCL/03/BS292981$