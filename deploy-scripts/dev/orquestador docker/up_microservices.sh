echo "---------- [CHECK NETWORK ]  ----------------"
if [ ! "$(docker network ls | grep lan-krugger)" ]; then
  echo "Creating lan-krugger network ..."
  docker network create lan-krugger
else
  echo "lan-krugger network exists."
fi

docker-compose pull
docker-compose up -d --remove-orphans
docker image prune -f

echo "UP CONTAINER SUCCESS"