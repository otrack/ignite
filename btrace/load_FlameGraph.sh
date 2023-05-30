#!/bin/bash

# kill all the children of the current process
trap "pkill -KILL -P $$; exit 255" SIGINT SIGTERM
trap "trap - SIGTERM && kill -- -$$" SIGINT SIGTERM EXIT

nbTest=""
clients="1 2 4 8 16 32 48"
list_stat="OVERALL-RunTime OVERALL-Throughput"
hosts="127.0.0.1"
workload="workloada"
operationcount=10000000
recordcount=100000
output_file_load="outputload.txt"
output_file_run="outputrun.txt"

nb_client="48"
cd $YCSB_HOME

#python2 $YCSB_HOME/bin/ycsb load ignite -p hosts=$hosts -s -P $YCSB_HOME/workloads/$workload -threads $nb_client -p operationcount=$operationcount -p recordcount=$recordcount > $IGNITE_HOME/$output_file_load
python2 $YCSB_HOME/bin/ycsb run ignite -p hosts=$hosts -s -P $YCSB_HOME/workloads/$workload -threads $nb_client -p operationcount=$operationcount -p recordcount=$recordcount > $IGNITE_HOME/$output_file_run 

cd $ASYNC_PROFILER_HOME

#./build/bin/asprof -f $YCSB_HOME/flameGraph_YCSB.html $!

cd $IGNITE_HOME/btrace
