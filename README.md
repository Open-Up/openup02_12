# Expose log to ElasticSearch

You will have a talk about the targetted log architecture.

To summarize it quicky here:

 - The application logs directly in **ElasticSearch**
 - **Kibana** can then search the logs and allow the user to browse it
 - We can skip the **LogStash** phase as we already have structured logs

## Steps to follow:

Install docker:

```
https://docs.docker.com/engine/installation/linux/debian/
```

Launch **ElasticSearch** and **Kibana** using docker commands:

```
docker run -d -p 9200:9200--name=elasticsearch elasticsearch:2.2.1
docker run --name kibana -e ELASTICSEARCH_URL=http://YOUR_COMPUTER_IP:9200 -p 5601:5601 -d kibana:4.4
```

Get the java application from [here](https://github.com/chibenwa/openup02_12) and clone it.

Launch it with maven:

```
mvn clean install
mvn exec:java
```

Connect to [logstash](http://127.0.0.1:5601). Configure it to use the logs-* index.

Browse the logs:

 - Show the rate of logs, info, warn error on the welcome page.
 - Find relevant information about the application.
 - Find the 5 different errors, and make two recommandations to the admin.