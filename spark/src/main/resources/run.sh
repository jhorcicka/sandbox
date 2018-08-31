#!/bin/bash

// having resource file on hdfs://.../tmp/national-parks.csv
spark-submit --master local[*] --class cz.hk.gmc.spark.Main spark-1.0-SNAPSHOT-jar-with-dependencies.jar