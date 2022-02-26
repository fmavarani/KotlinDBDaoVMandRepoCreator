
from distutils import filelist
import shutil
import os

directory = os.getcwd()

# filelist=glob.glob(directory+"/entities/*.kt")
filelist =os.listdir(directory+"/entities/")
dao=directory+"/template/DAO.kt"
vm=directory+"/template/VM.kt"
repo=directory+"/template/Repo.kt"

def filereplace(file,word,newowrd):
    with open(file) as r:
        text = r.read().replace(word, newowrd)
    with open(file, "w") as w:
        w.write(text)

def filecopy(directory, dao, filename, fileextention,folder,extention):
    shutil.copyfile(dao, directory+folder+filename+extention+fileextention)
    filereplace(directory+folder+filename+extention+fileextention,"template",filename)
    filereplace(directory+folder+filename+extention+fileextention,"Template",filename)

for files in filelist:
    filename=files.split(".")[0]
    fileextention="."+files.split(".")[1]
    filename=filename.replace("Entity","")
    filecopy(directory, dao, filename, fileextention,"/daos/","Dao")
    filecopy(directory, repo, filename, fileextention,"/repositories/","Repo")
    filecopy(directory, vm, filename, fileextention,"/viewmodels/","VM")