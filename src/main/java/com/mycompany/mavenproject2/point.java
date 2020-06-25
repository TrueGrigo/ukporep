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
public class point {
    private int x;
    private int y;
    
    
    point(int srcy,int rcx){
    this.x=rcx;
    this.y=srcy;
    }
    
    int GetX(){
    return this.x;
    }
    int GetY(){
    return this.y;
    }
}


