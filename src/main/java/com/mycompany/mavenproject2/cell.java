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
public class cell {
 public int parent_i;
 public int parent_j;
// f = g + h
public double f;
 public double g;
 public double h;
 public void cell(){
 this.parent_i=0;
 this.parent_j=0;

 this.f = -2;
 this.g=-2;
 this.h=-2;

 }}