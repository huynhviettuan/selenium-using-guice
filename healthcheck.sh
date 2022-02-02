echo "Checking if hub is ready - $HUB_HOST"


while [ "$( curl -s http://$HUB_HOST:4444/wd/hub/status | jq -r .value.ready )" != "true" ]
do
	sleep 1
done

#maven command
mvn test -DHUB_HOST=$HUB_HOST -DBROWSER=$BROWSER -DREMOTE.PLATFORM=$PLATFORM