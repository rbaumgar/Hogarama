#!/bin/bash

# source ${ENV_FILE}

if [[ "x$CHARTLOCATION" == "x" ]]; then
  CHARTLOCATION=/projects/Hogarama/OCP-Infrastructure/
fi

helm upgrade -i amq ${CHARTLOCATION}/amq --wait   \
  --set hogarama.amq.credentials.amq_password="blubb" \
  --set hogarama.amq.credentials.amq_user="tmp" \
  --set hogarama.amq.deploymentconfig.amq_truststore_password="765rjvb5rtzh8i7587fg" \
  --set hogarama.amq.deploymentconfig.amq_keystore_password="765rjvb5rtzh8i7587fg" \
  --set hogarama.amq.deploymentconfig.timezone="Europe/Vienna" \
  --set hogarama.amq.deploymentconfig.imageStream.name="amq-broker-72-openshift" \
  --set hogarama.amq.deploymentconfig.storage.size="1Gi"

