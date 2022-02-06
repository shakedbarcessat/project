# -*- coding: utf-8 -*-
"""
Created on Fri Jan 14 08:29:03 2022

@author: Student
"""
from PIL import Image
import change_pictures_names
import os
from numpy import expand_dims
from keras.preprocessing.image import load_img
from keras.preprocessing.image import img_to_array
from keras.preprocessing.image import ImageDataGenerator
from matplotlib import pyplot

def brighting_image(main_path, data, path, location, count, category):
    last_location=0
    for image in data:
        # load the image
        img = load_img(path+'\\'+image)
        # convert to numpy array
        data1 = img_to_array(img)
        # expand dimension to one sample
        samples = expand_dims(data1, 0)
        # create image data augmentation generator
        datagen = ImageDataGenerator(brightness_range=[0.2,1.0])
        # prepare iterator
        it = datagen.flow(samples, batch_size=1)
        # generate samples and plot
        for i in range(count): 
        	# define subplot
            pyplot.figure(i)
        	# generate batch of images
            batch = it.next()
        	# convert to unsigned integers for viewing
            image = batch[0].astype('uint8')
            im = Image.fromarray(image)
            im = im.resize((100, 100))
            path_im=os.path.join(main_path, r"augmented" +location+"\\"+category+"_img{0}.jpg".format(last_location))
            im.save(path_im)
        	# plot raw pixel data
            pyplot.imshow(image)
            last_location=last_location+1
            
        

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
