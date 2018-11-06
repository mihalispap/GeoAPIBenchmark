#!/bin/bash

echo "Benchmarking with:"$1" find alls"

echo "Benchmarking Elasticsearch"
start=$(date +%s)
for (( i=0; i<$1; i++ ))
do
        num=$(( ( RANDOM % 100 ) +1 ))
        curl -s "http://localhost:8090/egeo/list?page="$num > tmp
done
end=$(date +%s)
echo "Took:"$(( end-start))"secs"

echo ""

echo "Benchmarking Mongo"
start=$(date +%s)
for (( i=0; i<$1; i++ ))
do
	num=$(( ( RANDOM % 100 ) +1 ))
	curl -s "http://localhost:8090/mgeo/list?page="$num > tmp
done
end=$(date +%s)
echo "Took:"$(( end-start))"secs"

echo ""

echo "Benchmarking Postgres"
start=$(date +%s)
for (( i=0; i<$1; i++))
do
        num=$(( ( RANDOM % 100 ) +1))
        curl -s "http://localhost:8090/pgeo/list?page="$num > tmp
done
end=$(date +%s)
echo "Took:"$(( end-start))"secs"
