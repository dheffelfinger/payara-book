#!/bin/bash
for i in {1..6}; do 
  curl -i -XPOST http://localhost:8080/faulttolerance/webresources/faulttoleranceexample/threadpoolbulkhead?invocationNum=$i &
done

