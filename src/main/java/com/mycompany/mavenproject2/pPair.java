/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

/**
 *
 * @author BSmoke
 */
public class pPair {
    point a;
    double b;
    pPair(double y,point x){
    this.a=x;
    this.b=y;
    
    
    }
    point GetPoint(){
    return this.a;
    }
}
