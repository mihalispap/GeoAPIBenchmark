#!/bin/bash

echo "Benchmarking with:"$1" inserts"

echo "Benchmarking MySQL"
start=$(date +%s)
for (( i=0; i<20000; i++ ))
do
        curl -s -X POST "http://localhost:8090/mrecipe/create?name=name"$i > tmp
done

for (( i=0; i<500; i++ ))
do
        curl -s -X POST "http://localhost:8090/muser/create?name=name"$i > tmp
done

for (( i=0; i<5500; i++ ))
do
        curl -s -X POST "http://localhost:8090/muser/rate" > tmp
done
end=$(date +%s)
echo "Took:"$(( end-start))"secs"

exit

echo "Benchmarking Neo4j"
start=$(date +%s)
for (( i=0; i<20000; i++ ))
do
        curl -s -X POST "http://localhost:8090/nrecipe/create?name=name"$i > tmp
done

for (( i=0; i<500; i++ ))
do
        curl -s -X POST "http://localhost:8090/nuser/create?name=name"$i > tmp
done

for (( i=0; i<5500; i++ ))
do
        curl -s -X POST "http://localhost:8090/nuser/rate" > tmp
done
end=$(date +%s)
echo "Took:"$(( end-start))"secs"
