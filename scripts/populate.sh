#!/bin/bash

echo "Benchmarking with:"$1" inserts"

echo "Benchmarking Elasticsearch"
start=$(date +%s)
for (( i=0; i<$1; i++ ))
do
        num=$(awk -v min=3 -v max=17 -v seed=$RANDOM 'BEGIN{srand(seed);print min+rand()*(max-min+1)}')
        curl -s -X POST "http://localhost:8090/egeo/create?lon="$num"&lat="$num > tmp
done
end=$(date +%s)
echo "Took:"$(( end-start))"secs"

echo ""

echo "Benchmarking Mongo"
start=$(date +%s)
for (( i=0; i<$1; i++ ))
do
	num=$(awk -v min=3 -v max=17 -v seed=$RANDOM 'BEGIN{srand(seed);print min+rand()*(max-min+1)}')
	curl -s -X POST "http://localhost:8090/mgeo/create?lon="$num"&lat="$num > tmp
done
end=$(date +%s)
echo "Took:"$(( end-start))"secs"

echo ""

echo "Benchmarking Postgres"
start=$(date +%s)
for (( i=0; i<$1; i++))
do
        num=$(awk -v min=3 -v max=17 -v seed=$RANDOM 'BEGIN{srand(seed);print min+rand()*(max-min+1)}')
        curl -s -X POST "http://localhost:8090/pgeo/create?lon="$num"&lat="$num > tmp
done
end=$(date +%s)
echo "Took:"$(( end-start))"secs"
