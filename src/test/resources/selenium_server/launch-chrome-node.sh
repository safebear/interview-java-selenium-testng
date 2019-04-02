#! /bin/bash

java -Dwebdriver.chrome.driver='./chromedriver' -jar selenium-server-standalone-3.141.59.jar -role webdriver -hub 'http://localhost:4444/grid/register' -browser 'browserName = chrome, platform = MAC' -port 5556 &