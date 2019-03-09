docker run -d -p 9000:9000 -v /var/run/docker.sock:/var/run/docker.sock portainer/portainer
docker start SoftwareEngineering
docker exec -it SoftwareEngineering bash
mysql -u User -p
