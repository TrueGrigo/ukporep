/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.Iterator;
import java.util.Vector;
import java.util.Stack;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.LinkedHashSet;
/**
*
* @author grigo
*/
public class Program {
 private static boolean IsValid(int[][] grid, point x)
{
// Returns true if row number and column number
// is in range
return (x.GetY() >= 0) && (x.GetY() < grid.length) &&
(x.GetX() >= 0) && (x.GetX() < grid[0].length);
}
 private static boolean isUnBlocked(int[][] grid, point x)
{
// Returns true if the cell is not blocked else false
if (grid[x.GetY()][x.GetX()] == 1)
return (true);
else
return (false);
}
 private static boolean isDestination(point src, point dest)
{
if (src.GetX() == dest.GetX() && src.GetY() == dest.GetY())
return (true);
else
return (false);
}
 private static String tracePath(cell[][] cellDetails, point dest)
{
System.out.printf("\nThe Path is ");
int row = dest.GetY();
int col = dest.GetX();
Stack<point> Path=new Stack();
while (!(cellDetails[row][col].parent_i == row
&& cellDetails[row][col].parent_j == col))
 {

 point temp = new point(row,col);
 Path.push(temp);
int temp_row = cellDetails[row][col].parent_i;
int temp_col = cellDetails[row][col].parent_j;
row = temp_row;
col = temp_col;
}
point temp = new point(row,col);
 Path.push(temp);
 String answer="";
while (!Path.empty())
{
 point p = Path.pop();
System.out.printf("-> (%d,%d) ", p.GetY(), p.GetX());
answer+=p.GetY()+"-"+p.GetX()+",";
}
return answer;
}
 public static String AStarsearch(int grid[][], point src, point dest)
 {
 if(!IsValid(grid, src)){
 System.out.printf("Source is invalid\n");
return "Source is invalid";
}
// If the destination is out of range
if (IsValid(grid,dest) == false)
{
System.out.printf("Destination is invalid\n");
return "Destination is invalid";
}
// Either the source or the destination is blocked
if (isUnBlocked(grid,src) == false ||
isUnBlocked(grid,dest) == false)
{
System.out.printf("Source or the destination is blocked\n");
return "Source or the destination is blocked";
}
// If the destination cell is the same as source cell
if (isDestination(src, dest) == true)
{
System.out.printf("We are already at the destination\n");
return "We are already at the destination";
}
 int ROW = grid.length;
 int COL = grid[0].length;
 boolean closedList[][]= new boolean[ROW][COL];

// Declare a 2D array of structure to hold the details
//of that cell
cell[][] cellDetails = new cell[ROW][COL];
int i, j;
for (i = 0; i < ROW; i++)
{
for (j = 0; j < COL; j++)
{
 cellDetails[i][j]=new cell();
cellDetails[i][j].f = Double.MAX_VALUE;
cellDetails[i][j].g = Double.MAX_VALUE;
cellDetails[i][j].h = Double.MAX_VALUE;
cellDetails[i][j].parent_i = -1;
cellDetails[i][j].parent_j = -1;
}
}
// Initialising the parameters of the starting node
i = src.GetY(); j = src.GetX();
cellDetails[i][j].f = 0.0;
cellDetails[i][j].g = 0.0;
cellDetails[i][j].h = 0.0;
cellDetails[i][j].parent_i = i;
cellDetails[i][j].parent_j = j;
Set<pPair> openList = new LinkedHashSet<>();
// Put the starting cell on the open list and set its
// 'f' as 0
 point temp = new point(i,j);
 pPair temp2 = new pPair(0.0,temp);

 openList.add(temp2);
// We set this boolean value as false as initially
// the destination is not reached.
boolean foundDest = false;
String answer;
while (!openList.isEmpty())
{
 Iterator<pPair> AAA= openList.iterator();
pPair p = AAA.next();
// Remove this vertex from the open list
openList.remove(p);
// Add this vertex to the closed list
i = p.GetPoint().GetY();
j = p.GetPoint().GetX();
closedList[i][j] = true;
 double gNew, hNew, fNew;
//----------- 1st Successor (North) ------------
// Only process this cell if this is a valid one
if (IsValid(grid,i - 1, j) == true)
{
// If the destination cell is the same as the
// current successor
if (isDestination(i - 1, j, dest) == true)
{
// Set the Parent of the destination cell
cellDetails[i - 1][j].parent_i = i;
cellDetails[i - 1][j].parent_j = j;
System.out.printf("The destination cell is found\n");;
foundDest = true;
return tracePath(cellDetails, dest);
}
// If the successor is already on the closed
// list or if it is blocked, then ignore it.
// Else do the following
else if (closedList[i - 1][j] == false &&
isUnBlocked(grid, i - 1, j) == true)
{
gNew = cellDetails[i][j].g + 1.0;
hNew = calculateHValue(i - 1, j, dest);
fNew = gNew + hNew;
// If it isn’t on the open list, add it to
// the open list. Make the current square
// the parent of this square. Record the
// f, g, and h costs of the square cell
// OR
// If it is on the open list already, check
// to see if this path to that square is better,
// using 'f' cost as the measure.
if (cellDetails[i - 1][j].f == Double.MAX_VALUE ||
cellDetails[i - 1][j].f > fNew)
{
 temp = new point(i-1,j);
temp2 = new pPair(fNew,temp);
openList.add(temp2);
cellDetails[i-1][j]=new cell();
// Update the details of this cell
cellDetails[i - 1][j].f = fNew;
cellDetails[i - 1][j].g = gNew;
cellDetails[i - 1][j].h = hNew;
cellDetails[i - 1][j].parent_i = i;
cellDetails[i - 1][j].parent_j = j;
}
}
}
 //----------- 2nd Successor (South) ------------
// Only process this cell if this is a valid one
if (IsValid(grid,i + 1, j) == true)
{
// If the destination cell is the same as the
// current successor
if (isDestination(i + 1, j, dest) == true)
{
// Set the Parent of the destination cell
cellDetails[i + 1][j].parent_i = i;
cellDetails[i + 1][j].parent_j = j;
System.out.printf("The destination cell is found\n");
foundDest = true;
return tracePath(cellDetails, dest);
}
// If the successor is already on the closed
// list or if it is blocked, then ignore it.
// Else do the following
else if (closedList[i + 1][j] == false &&isUnBlocked(grid, i + 1, j) == true)
{
gNew = cellDetails[i][j].g + 1.0;
hNew = calculateHValue(i + 1, j, dest);
fNew = gNew + hNew;
// If it isn’t on the open list, add it to
// the open list. Make the current square
// the parent of this square. Record the
// f, g, and h costs of the square cell
// OR
// If it is on the open list already, check
// to see if this path to that square is better,
// using 'f' cost as the measure.
if (cellDetails[i + 1][j].f == Double.MAX_VALUE ||
cellDetails[i + 1][j].f > fNew)
{
 temp = new point(i+1,j);
temp2 = new pPair(fNew,temp);
openList.add(temp2);
// Update the details of this cell
 cellDetails[i+1][j]= new cell();
cellDetails[i + 1][j].f = fNew;
cellDetails[i + 1][j].g = gNew;
cellDetails[i + 1][j].h = hNew;
cellDetails[i + 1][j].parent_i = i;
cellDetails[i + 1][j].parent_j = j;
}
}
}
//----------- 3rd Successor (East) ------------
// Only process this cell if this is a valid one
if (IsValid(grid,i, j + 1) == true)
{
// If the destination cell is the same as the
// current successor
if (isDestination(i, j + 1, dest) == true)
{
// Set the Parent of the destination cell
cellDetails[i][j + 1].parent_i = i;
cellDetails[i][j + 1].parent_j = j;
System.out.printf("The destination cell is found\n");
foundDest = true;
return tracePath(cellDetails, dest);
}
// If the successor is already on the closed
// list or if it is blocked, then ignore it.
// Else do the following
else if (closedList[i][j + 1] == false &&
isUnBlocked(grid, i, j + 1) == true)
{
gNew = cellDetails[i][j].g + 1.0;
hNew = calculateHValue(i, j + 1, dest);
fNew = gNew + hNew;
// If it isn’t on the open list, add it to
// the open list. Make the current square
// the parent of this square. Record the
// f, g, and h costs of the square cell
// OR
// If it is on the open list already, check
// to see if this path to that square is better,
// using 'f' cost as the measure.
if (cellDetails[i][j + 1].f == Double.MAX_VALUE ||
cellDetails[i][j + 1].f > fNew)
{
 temp = new point(i,j+1);
 temp2 = new pPair(fNew,temp);
openList.add(temp2);
cellDetails[i][j+1]=new cell();
// Update the details of this cell
cellDetails[i][j + 1].f = fNew;
cellDetails[i][j + 1].g = gNew;
cellDetails[i][j + 1].h = hNew;
cellDetails[i][j + 1].parent_i = i;
cellDetails[i][j + 1].parent_j = j;
}
}
}
//----------- 4th Successor (West) ------------
// Only process this cell if this is a valid one
if (IsValid(grid, i, j - 1) == true)
{
// If the destination cell is the same as the
// current successor
if (isDestination(i, j - 1, dest) == true)
{
// Set the Parent of the destination cell
 cellDetails[i][j-1]=new cell();
cellDetails[i][j - 1].parent_i = i;
cellDetails[i][j - 1].parent_j = j;
System.out.printf("The destination cell is found\n");
foundDest = true;
return tracePath(cellDetails, dest);
}
// If the successor is already on the closed
// list or if it is blocked, then ignore it.
// Else do the following
else if (closedList[i][j - 1] == false &&
isUnBlocked(grid, i, j - 1) == true)
{
gNew = cellDetails[i][j].g + 1.0;
hNew = calculateHValue(i, j - 1, dest);
fNew = gNew + hNew;
// If it isn’t on the open list, add it to
// the open list. Make the current square
// the parent of this square. Record the
// f, g, and h costs of the square cell
// OR
// If it is on the open list already, check
// to see if this path to that square is better,
// using 'f' cost as the measure.
if (cellDetails[i][j - 1].f == Double.MAX_VALUE ||
cellDetails[i][j - 1].f > fNew)
{
 temp = new point(i,j-1);
 temp2 = new pPair(fNew,temp);
openList.add(temp2);
cellDetails[i][j-1]=new cell();
// Update the details of this cell
cellDetails[i][j - 1].f = fNew;
cellDetails[i][j - 1].g = gNew;
cellDetails[i][j - 1].h = hNew;
cellDetails[i][j - 1].parent_i = i;
cellDetails[i][j - 1].parent_j = j;
}
}
}
//----------- 5th Successor (North-East) ------------
// Only process this cell if this is a valid one
if (IsValid(grid,i - 1, j + 1) == true)
{
// If the destination cell is the same as the
// current successor
if (isDestination(i - 1, j + 1, dest) == true)
{
// Set the Parent of the destination cell
 cellDetails[i-1][j+1]=new cell();
cellDetails[i - 1][j + 1].parent_i = i;
cellDetails[i - 1][j + 1].parent_j = j;
System.out.printf("The destination cell is found\n");
foundDest = true;
return tracePath(cellDetails, dest);
}
// If the successor is already on the closed
// list or if it is blocked, then ignore it.
// Else do the following
else if (closedList[i - 1][j + 1] == false &&
isUnBlocked(grid, i - 1, j + 1) == true)
{
gNew = cellDetails[i][j].g + 1.414;
hNew = calculateHValue(i - 1, j + 1, dest);
fNew = gNew + hNew;
// If it isn’t on the open list, add it to
// the open list. Make the current square
// the parent of this square. Record the
// f, g, and h costs of the square cell
// OR
// If it is on the open list already, check
// to see if this path to that square is better,
// using 'f' cost as the measure.
if (cellDetails[i - 1][j + 1].f == Double.MAX_VALUE ||
cellDetails[i - 1][j + 1].f > fNew)
{
temp = new point(i-1,j+1);
 temp2 = new pPair(fNew,temp);
openList.add(temp2);
// Update the details of this cell
cellDetails[i - 1][j + 1].f = fNew;
cellDetails[i - 1][j + 1].g = gNew;
cellDetails[i - 1][j + 1].h = hNew;
cellDetails[i - 1][j + 1].parent_i = i;
cellDetails[i - 1][j + 1].parent_j = j;
}
}
}
//----------- 6th Successor (North-West) ------------
// Only process this cell if this is a valid one
if (IsValid(grid, i - 1, j - 1) == true)
{
// If the destination cell is the same as the
// current successor
if (isDestination(i - 1, j - 1, dest) == true)
{
// Set the Parent of the destination cell
cellDetails[i - 1][j - 1].parent_i = i;
cellDetails[i - 1][j - 1].parent_j = j;
System.out.printf("The destination cell is found\n");
foundDest = true;
return tracePath(cellDetails, dest);
}
// If the successor is already on the closed
// list or if it is blocked, then ignore it.
// Else do the following
else if (closedList[i - 1][j - 1] == false &&
isUnBlocked(grid, i - 1, j - 1) == true)
{
gNew = cellDetails[i][j].g + 1.414;
hNew = calculateHValue(i - 1, j - 1, dest);
fNew = gNew + hNew;
// If it isn’t on the open list, add it to
// the open list. Make the current square
// the parent of this square. Record the
// f, g, and h costs of the square cell
// OR
// If it is on the open list already, check
// to see if this path to that square is better,
// using 'f' cost as the measure.
if (cellDetails[i - 1][j - 1].f == Double.MAX_VALUE ||
cellDetails[i - 1][j - 1].f > fNew)
{
 temp = new point(i-1,j-1);
 temp2 = new pPair(fNew,temp);
 openList.add(temp2);
// Update the details of this cell
cellDetails[i - 1][j - 1].f = fNew;
cellDetails[i - 1][j - 1].g = gNew;
cellDetails[i - 1][j - 1].h = hNew;
cellDetails[i - 1][j - 1].parent_i = i;
cellDetails[i - 1][j - 1].parent_j = j;
}
}
}
//----------- 7th Successor (South-East) ------------
// Only process this cell if this is a valid one
if (IsValid(grid,i + 1, j + 1) == true)
{
// If the destination cell is the same as the
// current successor
if (isDestination(i + 1, j + 1, dest) == true)
{
// Set the Parent of the destination cell
cellDetails[i + 1][j + 1].parent_i = i;
cellDetails[i + 1][j + 1].parent_j = j;
System.out.printf("The destination cell is found\n");
foundDest = true;
return tracePath(cellDetails, dest);
}
// If the successor is already on the closed
// list or if it is blocked, then ignore it.
// Else do the following
else if (closedList[i + 1][j + 1] == false &&
isUnBlocked(grid, i + 1, j + 1) == true)
{
gNew = cellDetails[i][j].g + 1.414;
hNew = calculateHValue(i + 1, j + 1, dest);
fNew = gNew + hNew;
// If it isn’t on the open list, add it to
// the open list. Make the current square
// the parent of this square. Record the
// f, g, and h costs of the square cell
// OR
// If it is on the open list already, check
// to see if this path to that square is better,
// using 'f' cost as the measure.
if (cellDetails[i + 1][j + 1].f == Double.MAX_VALUE ||
cellDetails[i + 1][j + 1].f > fNew)
{
 temp = new point(i+1,j+1);
 temp2 = new pPair(fNew,temp);
 openList.add(temp2);
// Update the details of this cell
cellDetails[i + 1][j + 1].f = fNew;
cellDetails[i + 1][j + 1].g = gNew;
cellDetails[i + 1][j + 1].h = hNew;
cellDetails[i + 1][j + 1].parent_i = i;
cellDetails[i + 1][j + 1].parent_j = j;
}
}
}
//----------- 8th Successor (South-West) ------------
// Only process this cell if this is a valid one
if (IsValid(grid,i + 1, j - 1) == true)
{
// If the destination cell is the same as the
// current successor
if (isDestination(i + 1, j - 1, dest) == true)
{
// Set the Parent of the destination cell
cellDetails[i + 1][j - 1].parent_i = i;
cellDetails[i + 1][j - 1].parent_j = j;
System.out.printf("The destination cell is found\n");
foundDest = true;
return tracePath(cellDetails, dest);
}
// If the successor is already on the closed
// list or if it is blocked, then ignore it.
// Else do the following
else if (closedList[i + 1][j - 1] == false &&
isUnBlocked(grid, i + 1, j - 1) == true)
{
gNew = cellDetails[i][j].g + 1.414;
hNew = calculateHValue(i + 1, j - 1, dest);
fNew = gNew + hNew;
// If it isn’t on the open list, add it to
// the open list. Make the current square
// the parent of this square. Record the
// f, g, and h costs of the square cell
// OR
// If it is on the open list already, check
// to see if this path to that square is better,
// using 'f' cost as the measure.
if (cellDetails[i + 1][j - 1].f == Double.MAX_VALUE ||
cellDetails[i + 1][j - 1].f > fNew)
{
 temp = new point(i+1,j-1);
 temp2 = new pPair(fNew,temp);
openList.add(temp2);
// Update the details of this cell
cellDetails[i + 1][j - 1].f = fNew;
cellDetails[i + 1][j - 1].g = gNew;
cellDetails[i + 1][j - 1].h = hNew;
cellDetails[i + 1][j - 1].parent_i = i;
cellDetails[i + 1][j - 1].parent_j = j;
}
}
}
}
// When the destination cell is not found and the open
// list is empty, then we conclude that we failed to
// reach the destiantion cell. This may happen when the
// there is no way to destination cell (due to blockages)
if (foundDest == false)
System.out.printf("Failed to find the Destination Cell\n");
return "Failed to find the Destination Cell";
 }
 private static boolean IsValid(int[][] grid, int i, int j) {
 return (i >= 0) && (i < grid.length) &&
(j >= 0) && (j < grid[0].length);
 }
 private static boolean isDestination(int i, int j, point dest) {
 if (j == dest.GetX() && i == dest.GetY())
return (true);
else
return (false); }
 private static boolean isUnBlocked(int[][] grid, int i, int j) {
 // Returns true if the cell is not blocked else false
if (grid[i][j] == 1)
return (true);
else
return (false); }
 private static double calculateHValue(int i, int j, point dest) {
 return ((double)Math.sqrt((i - dest.GetY()) * (i - dest.GetY())+ (j - dest.GetX()) * (j - dest.GetX()))); }}