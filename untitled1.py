# -*- coding: utf-8 -*-
"""
Created on Mon Dec 13 15:18:21 2021
Not good
"""
# example of loading an image with the Keras API
from keras.preprocessing.image import load_img
# load the image
img = load_img("D:newbottle.jpg")
# report details about the image
print(type(img))
print(img.format)
print(img.mode)
print(img.size)
# show the image
img.show()

# resize image and force a new shape
from PIL import Image
# load the image
image = Image.open("D:newbottle.jpg")
# report the size of the image
print(image.size)
# resize image and ignore original aspect ratio
img_resized = image.resize((80,80))
# report the size of the thumbnail
print(img_resized.size)
# show the image
img_resized.show()

# example of brighting image augmentation
from PIL import Image
from numpy import expand_dims
from keras.preprocessing.image import load_img
from keras.preprocessing.image import img_to_array
from keras.preprocessing.image import ImageDataGenerator
from matplotlib import pyplot
data = img_to_array(img)
# expand dimension to one sample
samples = expand_dims(data, 0)
# create image data augmentation generator
datagen = ImageDataGenerator(brightness_range=[1.0,3])
# prepare iterator
it = datagen.flow(samples, batch_size=1)
# generate samples and plot
for i in range(9):
# define subplot
    pyplot.subplot(330 + 1 + i)
    # generate batch of images
    batch = it.next()
    # convert to unsigned integers for viewing
    image = batch[0].astype('uint8')
    # plot raw pixel data
pyplot.imshow(image)
# show the figure

# example of random rotation image augmentation
img = load_img("D:newbottle.jpg")
# convert to numpy array
data = img_to_array(img)
# expand dimension to one sample
samples = expand_dims(data, 0)
# create image data augmentation generator
datagen = ImageDataGenerator(rotation_range=90)
# prepare iterator
it = datagen.flow(samples, batch_size=1)
# generate samples and plot
for i in range(9):
	# define subplot
	pyplot.subplot(330 + 1 + i)
	# generate batch of images
	batch = it.next()
	# convert to unsigned integers for viewing
	image = batch[0].astype('uint8')
	# plot raw pixel data
	pyplot.imshow(image)
# show the figure
pyplot.show()





















