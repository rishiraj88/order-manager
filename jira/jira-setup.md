order-manager jira
==
docker volume create --name jiradatastore
docker run -v jiradatastore:/var/atlassian/application-data/jira --name="jira" -d -p 80:8080 atlassian/jira-software

Success. Jira is now available on http://localhost:8080⁠
==
jiraPass2234%%
postgreSQL
port 5432

db name: jira
username:rishiraj
password
schema: public