/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;
import java.util.Arrays;
/**
 *
 * @author cole.marquard
 */
    
public class NN {
    int INputlayerSize = 10;
    int outputlayerSize = 10;
    int m = 10;
    int nodes = 200; 
    
        double[][] W1 = Np.random(nodes, INputlayerSize);
        double[][] b1 = new double[nodes][m];

        double[][] W2 = Np.random(outputlayerSize, nodes);
        double[][] b2 = new double[outputlayerSize][m];
    
    public double[][] Predict(double [][] X){
      X = Np.T(X);
    
    
      double[][] Z1 = Np.add(Np.dot(W1, X), b1);
      double[][] A1 = Np.sigmoid(Z1);

            //LAYER 2
      double[][] Z2 = Np.add(Np.dot(W2, A1), b2);
      double[][] A2 = Np.sigmoid(Z2);
      
      return A2;

    }
    
    
    public void Train(double [][] X , double [] [] Y){
        X = Np.T(X);
        Y = Np.T(Y);
    
      double[][] Z1 = Np.add(Np.dot(W1, X), b1);
            double[][] A1 = Np.sigmoid(Z1);

            //LAYER 2
            double[][] Z2 = Np.add(Np.dot(W2, A1), b2);
            double[][] A2 = Np.sigmoid(Z2);

            double cost = Np.cross_entropy(m, Y, A2);
            
            
            
            //costs.getData().add(new XYChart.Data(i, cost));
         
            // Back Prop
            //LAYER 2
            double[][] dZ2 = Np.subtract(A2, Y);
            double[][] dW2 = Np.divide(Np.dot(dZ2, Np.T(A1)), m);
            double[][] db2 = Np.divide(dZ2, m);

            //LAYER 1
            double[][] dZ1 = Np.multiply(Np.dot(Np.T(W2), dZ2), Np.subtract(1.0, Np.power(A1, 2)));
            double[][] dW1 = Np.divide(Np.dot(dZ1, Np.T(X)), m);
            double[][] db1 = Np.divide(dZ1, m);

            // G.D
            W1 = Np.subtract(W1, Np.multiply(0.01, dW1));
            b1 = Np.subtract(b1, Np.multiply(0.01, db1));

            W2 = Np.subtract(W2, Np.multiply(0.01, dW2));
            b2 = Np.subtract(b2, Np.multiply(0.01, db2));

    }
}
