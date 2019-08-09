#!/bin/bash

// having resource file on hdfs://.../tmp/national-parks.csv
spark-submit --master local[*] --class nl.hi.kuba.spark.Main spark-1.0-SNAPSHOT-jar-with-dependencies.jar