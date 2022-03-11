package com.yizhi.common.util;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel;
import org.apache.spark.mllib.recommendation.Rating;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;

public class SparkRecommendModel {
    public MatrixFactorizationModel model(JavaPairRDD<Long, Rating> ratings) {

    }
}
