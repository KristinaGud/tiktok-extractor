TikTok Extractor
================

API uses Selenium Chrome WebDriver to search for keyword in TikTok and collect related to keyword videos ids, on which base urls to videos and their comments are generated.

From collected data API extracts: 
* number of analysed videos, 
* the number of unique tags found in videos descriptions,
* 10 most popular tags,
* all comments related to videos,
* time gap when comments were published. 

JSP were used to present analysed results on web: after user submits keyword, JSON containing all data is returned.

