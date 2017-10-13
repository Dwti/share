import  urllib
from urllib import request
import time
from bs4 import  BeautifulSoup
import re
#获取URL
# http://www.yuncaijing.com/insider/simple.html
#https://www.cailianpress.com/index
baseurl = "http://www.yuncaijing.com"
# re_url="http://www.yuncaijing.com/insider/"
def deal_url(url):
    url_list=[]
    for i in range(0,15):
        i=str(i)
        if i==0:
            re_url = url + "simple.html"
            url_list.append(re_url)
        else:
            re_url = url + "page_" + i + ".html"
            url_list.append(re_url)
            url=url
    return url_list

#改变为soup对象

def download_webcontent():
    re_url = "http://www.yuncaijing.com/insider/"
    time.sleep(2)
    url_list = deal_url(re_url)
    # print(url_list)
    soups = []
    for url in url_list:
        # 设置请求头文件信息
        headers = {'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36'
        }
        req=urllib.request.Request(url,headers=headers)
        resq=urllib.request.urlopen(req,timeout=10)
        if resq.getcode() == 200:
            html_doc = resq.read()
            soup=BeautifulSoup(html_doc,"html.parser")
            soups.append(soup)
    return soups
def find_all_keynews(keyword):
    news=[]
    soups = download_webcontent()
    for soup in soups:
        links = soup.find_all("a", class_="title", text=re.compile(keyword))
        for link in links:
            # print("【" + link["title"] + "】" + baseurl + link["href"])
            news.append("【" + link["title"] + "】" + baseurl + link["href"])
    return  news
def write_files(keyword):
    # keyword = "深圳"
    news=find_all_keynews(keyword)
    # print(news)
    with open('C:/Users/srt-k12001/Desktop/医疗.txt', 'w') as f:
        if len(news)==0:
            f.write("没找到相关资源啦！！！")
        else:
            for new in news:
                f.write(new+'\n')

# if __name__ == '__main__':
#     write_files("深圳")


