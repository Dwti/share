from tkinter import *
import yuncaijing_crawdler
root = Tk()
#sticky=N/S/E/W(分别是上下左右排布)
label_1 = Label(root, text="请输入抓取关键字").grid(row=0,sticky=E)
entry_1=Entry(root)
entry_1.grid(row=0,column=1,sticky=E)
def get_keywords():
   # print(entry_1.get())
   keywords=entry_1.get()
   yuncaijing_crawdler.write_files(keywords)
   entry_1.delete(0,END)
button=Button(root,text="抓取",command=get_keywords).grid(row=2)
root.mainloop()