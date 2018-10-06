# learning-elasticsearch-java
 Prerequisite : Java 8 and Later version 

### Start Elasticsearch
1) Download elasticsearch from [here](https://www.elastic.co/downloads/elasticsearch)   
2) Extract downloaded elasticsearch     
3) cd elasticsearch-2.4.X       
4) $ bin/elasticsearch     

### Insert data into elasticsearch
      $ sh initial-data.sh

### Clone the code:
     $ git clone git@github.com:techmonad/learning-elasticsearch-java.git
### Run project 
    $ mvn package
    $ mvn exec:java -Dexec.mainClass="com.techmonad.es.app.ESApp"
    

