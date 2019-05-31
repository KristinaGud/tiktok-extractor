TikTok Extractor
================

App uses Selenium Chrome WebDriver to search for keyword in TikTok and collect videos ids related to keyword, on which base urls to videos and their comments are generated.

From collected data app extracts: 
* number of analysed videos, 
* the number of unique tags found in videos descriptions,
* 10 most popular tags,
* all comments related to videos,
* time span when comments were published. 

JSP were used to present analysed results: after user submits keyword, JSON containing all data is returned.

