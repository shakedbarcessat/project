# -*- coding: utf-8 -*-
"""
Created on Thu Mar 31 10:30:47 2022

@author: Shaked Barcessat
"""
import changing_data
import nural_network
from matplotlib import pyplot
import tensorflow as tf
import numpy as np
import os


def print_menu():
    print("\nChoose an option")
    print("1. Train the model")
    print("2. Test the model")
    print("3. Predict an image")
    print("4. Exit")
    choice = input("Enter your choice: ")
    while(choice.isdigit()==False or (int(choice) < 1) or (int(choice) > 4)):
        print("Sorry, this is not a valid input. ")
        choice = input("Please enter your choice again: ")
    return int(choice)



def option_1(model, trainX, trainY, validationX, validationY):
    nural_network.train_model(model, trainX, trainY, validationX, validationY)
    print("Done! \nThe model is successfully trained.")
    return True


def option_2(testX, testY):
    loaded_model = tf.keras.models.load_model('savedmodel/model.h5')
    nural_network.evaluate_model(loaded_model, testX, testY)
    


    
def option_3():
    isExist = os.path.exists('savedmodel/model.h5')
    if not isExist:
        print("Folder does not exist")
        return
    loaded_model = tf.keras.models.load_model('savedmodel/model.h5')
    img_num = input("Choose a number between 1 to 11328: ")
    if int(img_num)>11328 or int(img_num)<1:
        print("choose again")
        return
    i = int(img_num) - 1 #index of the chosen image
    label_images, test_images=changing_data.send_test_images()
    pyplot.imshow(test_images[i])
    pyplot.show()
    print("You choose to predict ", label_images[i])
    
    img = test_images[i]
    img = np.expand_dims(img, axis=0)
    
    result = loaded_model.predict(img)
    print(result)
    print("Prediction is: ",round(float(result)))
    #print(result[0], test_labels_orl[i])
    if (round(float(result)) == label_images[i]):
        print("Prediction is true")
    else:
        print("Prediction is wrong")


def main():

    model= nural_network.define_model()
    trainX, trainY, validationX, validationY=changing_data.send_train()
    testX, testY=changing_data.send_test()
    
    print("\nHello! \nMy project is about recycling classification.")
    user_choice = print_menu()
    while not user_choice == 4:
        if user_choice == 1:
            option_1(model, trainX, trainY, validationX, validationY)           
        elif user_choice == 2:
            option_2(testX, testY)
        else:
            option_3()    
        user_choice = print_menu()
   

if __name__ == "__main__":
    main() 





"""
from tkinter import *

root = Tk()
root.title("Shaked App")

root.geometry("410x410")

def after_start():
    train=Button(root,text="Lets start!", bg = 'yellow', command=after_start)
    test=Button(root,text="Lets start!", bg = 'yellow', command=after_start)
    predict=Button(root,text="Lets start!", bg = 'yellow', command=after_start)
    
    
label = Label(root, text="My machine learning project is about recycling classification.")   
label.pack() 
button1=Button(root,text="Lets start!", bg = 'yellow', command=after_start)
button1.pack()


root.mainloop()


path = r'savedmodel'
communicate=input("Select model, type yes if you want to create new model or no if you want to use the default model.")

while communicate!= "yes" and communicate!="no":
    communicate=input("Select model, type yes if you want to create new model or no if you want to use the default model.")

if communicate=="yes":
    changing_data.main()
else:
    #communicate=no
    loaded_model = tf.keras.models.load_model('savedmodel/model.h5')
    loaded_model.summary()

"""    

