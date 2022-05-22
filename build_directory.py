# -*- coding: utf-8 -*-
"""
Created on Fri Jan 14 09:07:05 2022

@author: Student
"""

import build_directory
import os
"""
import patoolib
patoolib.extract_archive(r"D:\ShakedProjectY\DATASET.rar", outdir=r"D:\ShakedProjectY\out")
"""
def check_if_exists(path):
    isExist = os.path.exists(path)
    if not isExist:
        os.makedirs(path)

"""
def build_directories(path):
    groups= ["train", "test", "validation", "sorted", "augmented", "final", "out"]
    recycle_not_recycle= ["recycle", "not-recycle"]
    classes= ["books", "cardboard", "glass bottles", "plastic bottles", "newspapers", "fruits", "meat", "nature", "vegtables"]
    for group_ in groups:
        for rec_ in recycle_not_recycle:
            for class_ in classes:
                check_if_exists()
"""        

def build_directories(path):
    check_if_exists(path)
    check_if_exists(os.path.join(path, r'out'))
    check_if_exists(os.path.join(path, r'out\sorted'))
    check_if_exists(os.path.join(path, r'out\sorted\recycle'))
    check_if_exists(os.path.join(path, r'out\sorted\recycle\books'))
    check_if_exists(os.path.join(path, r'out\sorted\recycle\cardboard'))
    check_if_exists(os.path.join(path, r'out\sorted\recycle\glass bottles'))
    check_if_exists(os.path.join(path, r'out\sorted\recycle\newspapers'))
    check_if_exists(os.path.join(path, r'out\sorted\not-recycle'))
    check_if_exists(os.path.join(path, r'out\sorted\not-recycle\fruits'))
    check_if_exists(os.path.join(path, r'out\sorted\not-recycle\meat'))
    check_if_exists(os.path.join(path, r'out\sorted\not-recycle\nature'))
    check_if_exists(os.path.join(path, r'out\sorted\not-recycle\vegtables'))
    check_if_exists(os.path.join(path, r'DATASET\TRAIN\R'))
    check_if_exists(os.path.join(path, r'train'))
    check_if_exists(os.path.join(path, r'train\recycle'))
    check_if_exists(os.path.join(path, r'train\recycle\books'))
    check_if_exists(os.path.join(path, r'train\recycle\cardboard'))
    check_if_exists(os.path.join(path, r'train\recycle\glass bottles'))
    check_if_exists(os.path.join(path, r'train\recycle\plastic bottles'))
    check_if_exists(os.path.join(path, r'train\recycle\newspapers'))
    check_if_exists(os.path.join(path, r'train\not-recycle'))
    check_if_exists(os.path.join(path, r'train\not-recycle\fruits'))
    check_if_exists(os.path.join(path, r'train\not-recycle\meat'))
    check_if_exists(os.path.join(path, r'train\not-recycle\nature'))
    check_if_exists(os.path.join(path, r'train\not-recycle\vegtables'))
    check_if_exists(os.path.join(path, r'test'))
    check_if_exists(os.path.join(path, r'test\recycle'))
    check_if_exists(os.path.join(path, r'test\recycle\books'))
    check_if_exists(os.path.join(path, r'test\recycle\cardboard'))
    check_if_exists(os.path.join(path, r'test\recycle\glass bottles'))
    check_if_exists(os.path.join(path, r'test\recycle\plastic bottles'))
    check_if_exists(os.path.join(path, r'test\recycle\newspapers'))
    check_if_exists(os.path.join(path, r'test\not-recycle'))
    check_if_exists(os.path.join(path, r'test\not-recycle\fruits'))
    check_if_exists(os.path.join(path, r'test\not-recycle\meat'))
    check_if_exists(os.path.join(path, r'test\not-recycle\nature'))
    check_if_exists(os.path.join(path, r'test\not-recycle\vegtables'))
    check_if_exists(os.path.join(path, r'validation'))
    check_if_exists(os.path.join(path, r'validation\recycle'))
    check_if_exists(os.path.join(path, r'validation\recycle\books'))
    check_if_exists(os.path.join(path, r'validation\recycle\cardboard'))
    check_if_exists(os.path.join(path, r'validation\recycle\glass bottles'))
    check_if_exists(os.path.join(path, r'validation\recycle\plastic bottles'))
    check_if_exists(os.path.join(path, r'validation\recycle\newspapers'))
    check_if_exists(os.path.join(path, r'validation\not-recycle'))
    check_if_exists(os.path.join(path, r'validation\not-recycle\fruits'))
    check_if_exists(os.path.join(path, r'validation\not-recycle\meat'))
    check_if_exists(os.path.join(path, r'validation\not-recycle\nature'))
    check_if_exists(os.path.join(path, r'validation\not-recycle\vegtables'))
    check_if_exists(os.path.join(path, r'augmented'))
    check_if_exists(os.path.join(path, r'sorted'))
    check_if_exists(os.path.join(path, r'sorted\recycle'))
    check_if_exists(os.path.join(path, r'sorted\recycle\books'))
    check_if_exists(os.path.join(path, r'sorted\recycle\cardboard'))
    check_if_exists(os.path.join(path, r'sorted\recycle\glass bottles'))
    check_if_exists(os.path.join(path, r'sorted\recycle\newspapers'))
    check_if_exists(os.path.join(path, r'sorted\not-recycle'))
    check_if_exists(os.path.join(path, r'sorted\not-recycle\fruits'))
    check_if_exists(os.path.join(path, r'sorted\not-recycle\meat'))
    check_if_exists(os.path.join(path, r'sorted\not-recycle\nature'))
    check_if_exists(os.path.join(path, r'sorted\not-recycle\vegtables'))
    check_if_exists(os.path.join(path, r'augmented\recycle'))
    check_if_exists(os.path.join(path, r'augmented\recycle\books'))
    check_if_exists(os.path.join(path, r'augmented\recycle\cardboard'))
    check_if_exists(os.path.join(path, r'augmented\recycle\glass bottles'))
    check_if_exists(os.path.join(path, r'augmented\recycle\newspapers'))
    check_if_exists(os.path.join(path, r'augmented\not-recycle'))
    check_if_exists(os.path.join(path, r'augmented\not-recycle\fruits'))
    check_if_exists(os.path.join(path, r'augmented\not-recycle\meat'))
    check_if_exists(os.path.join(path, r'augmented\not-recycle\nature'))
    check_if_exists(os.path.join(path, r'augmented\not-recycle\vegtables'))
    check_if_exists(os.path.join(path, r'final\recycle'))
    check_if_exists(os.path.join(path, r'final\recycle\books'))
    check_if_exists(os.path.join(path, r'final\recycle\cardboard'))
    check_if_exists(os.path.join(path, r'final\recycle\glass bottles'))
    check_if_exists(os.path.join(path, r'final\recycle\newspapers'))
    check_if_exists(os.path.join(path, r'final\not-recycle'))
    check_if_exists(os.path.join(path, r'final\not-recycle\fruits'))
    check_if_exists(os.path.join(path, r'final\not-recycle\meat'))
    check_if_exists(os.path.join(path, r'final\not-recycle\nature'))
    check_if_exists(os.path.join(path, r'final\not-recycle\vegtables'))
    
        
        
