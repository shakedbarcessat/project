# -*- coding: utf-8 -*-
"""
Created on Fri Jan 14 09:07:05 2022

@author: Student
"""

import build_directory
import os

def check_if_exists(path):
    isExist = os.path.exists(path)
    if not isExist:
        os.makedirs(path)

def build_directories():
    check_if_exists('D:')
    check_if_exists('D:\ShakedProjectY\DATASET\TRAIN\R')
    check_if_exists(r'D:\ShakedProjectY\train')
    check_if_exists(r'D:\ShakedProjectY\train\recycle')
    check_if_exists(r'D:\ShakedProjectY\train\recycle\books')
    check_if_exists(r'D:\ShakedProjectY\train\recycle\cardboard')
    check_if_exists(r'D:\ShakedProjectY\train\recycle\glass bottles')
    check_if_exists(r'D:\ShakedProjectY\train\recycle\plastic bottles')
    check_if_exists(r'D:\ShakedProjectY\train\recycle\newspapers')
    check_if_exists(r'D:\ShakedProjectY\train\not-recycle')
    check_if_exists(r'D:\ShakedProjectY\train\not-recycle\fruits')
    check_if_exists(r'D:\ShakedProjectY\train\not-recycle\meat')
    check_if_exists(r'D:\ShakedProjectY\train\not-recycle\nature')
    check_if_exists(r'D:\ShakedProjectY\train\not-recycle\vegtables')
    check_if_exists(r'D:\ShakedProjectY\test')
    check_if_exists(r'D:\ShakedProjectY\test\recycle')
    check_if_exists(r'D:\ShakedProjectY\test\recycle\books')
    check_if_exists(r'D:\ShakedProjectY\test\recycle\cardboard')
    check_if_exists(r'D:\ShakedProjectY\test\recycle\glass bottles')
    check_if_exists(r'D:\ShakedProjectY\test\recycle\plastic bottles')
    check_if_exists(r'D:\ShakedProjectY\test\recycle\newspapers') 
    check_if_exists(r'D:\ShakedProjectY\test\not-recycle')
    check_if_exists(r'D:\ShakedProjectY\test\not-recycle\fruits')
    check_if_exists(r'D:\ShakedProjectY\test\not-recycle\meat')
    check_if_exists(r'D:\ShakedProjectY\test\not-recycle\nature')
    check_if_exists(r'D:\ShakedProjectY\test\not-recycle\vegtables')
    check_if_exists(r'D:\ShakedProjectY\validation')
    check_if_exists(r'D:\ShakedProjectY\validation\recycle')
    check_if_exists(r'D:\ShakedProjectY\validation\recycle\books')
    check_if_exists(r'D:\ShakedProjectY\validation\recycle\cardboard')
    check_if_exists(r'D:\ShakedProjectY\validation\recycle\glass bottles')
    check_if_exists(r'D:\ShakedProjectY\validation\recycle\plastic bottles')
    check_if_exists(r'D:\ShakedProjectY\validation\recycle\newspapers') 
    check_if_exists(r'D:\ShakedProjectY\validation\not-recycle')  
    check_if_exists(r'D:\ShakedProjectY\validation\not-recycle\fruits')
    check_if_exists(r'D:\ShakedProjectY\validation\not-recycle\meat')
    check_if_exists(r'D:\ShakedProjectY\validation\not-recycle\nature')
    check_if_exists(r'D:\ShakedProjectY\validation\not-recycle\vegtables')
    check_if_exists(r'D:\ShakedProjectY\augmented')
    check_if_exists(r'D:\ShakedProjectY\sorted')
    check_if_exists(r'D:\ShakedProjectY\sorted\recycle')
    check_if_exists(r'D:\ShakedProjectY\sorted\recycle\books')
    check_if_exists(r'D:\ShakedProjectY\sorted\recycle\cardboard')
    check_if_exists(r'D:\ShakedProjectY\sorted\recycle\glass bottles')
    check_if_exists(r'D:\ShakedProjectY\sorted\recycle\newspapers') 
    check_if_exists(r'D:\ShakedProjectY\sorted\not-recycle')
    check_if_exists(r'D:\ShakedProjectY\sorted\not-recycle\fruits')
    check_if_exists(r'D:\ShakedProjectY\sorted\not-recycle\meat')
    check_if_exists(r'D:\ShakedProjectY\sorted\not-recycle\nature')
    check_if_exists(r'D:\ShakedProjectY\sorted\not-recycle\vegtables')
    check_if_exists(r'D:\ShakedProjectY\augmented\recycle')
    check_if_exists(r'D:\ShakedProjectY\augmented\recycle\books')
    check_if_exists(r'D:\ShakedProjectY\augmented\recycle\cardboard')
    check_if_exists(r'D:\ShakedProjectY\augmented\recycle\glass bottles')
    check_if_exists(r'D:\ShakedProjectY\augmented\recycle\newspapers') 
    check_if_exists(r'D:\ShakedProjectY\augmented\not-recycle')
    check_if_exists(r'D:\ShakedProjectY\augmented\not-recycle\fruits')
    check_if_exists(r'D:\ShakedProjectY\augmented\not-recycle\meat')
    check_if_exists(r'D:\ShakedProjectY\augmented\not-recycle\nature')
    check_if_exists(r'D:\ShakedProjectY\augmented\not-recycle\vegtables')
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    