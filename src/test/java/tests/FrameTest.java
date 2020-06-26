/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

/**
 *
 * @author BSmoke
 */

import com.mycompany.mavenproject2.Frame;
import com.mycompany.mavenproject2.point;
import static com.mycompany.mavenproject2.Program.AStarsearch;
import com.mycompany.mavenproject2.point;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
/**
*
* @author black
*/
public class FrameTest {
 private FrameFixture window;


 @Test
 public void ExpecterPath1() throws InterruptedException {
int grid[][] =
{
{ 1, 1, 1, 1, 1, 0, 1, 1, 1, 1 },
{ 1, 1, 1, 1, 1, 0, 1, 0, 0, 1 },
{ 1, 1, 1, 1, 1, 0, 1, 0, 1, 1 },
{ 1, 1, 1, 1, 1, 0, 1, 1, 0, 1 },
{ 1, 1, 1, 1, 1, 0, 1, 1, 0, 1 },
{ 1, 1, 1, 1, 1, 0, 1, 0, 1, 1 },
{ 1, 1, 1, 1, 1, 0, 1, 1, 0, 1 },
{ 1, 1, 1, 1, 1, 0, 0, 0, 0, 1 },
{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
};
point src = new point(0,0);
point dest = new point(4,9);
 assertEquals("0-0,1-0,2-0,3-0,4-1,5-2,6-3,7-4,8-5,8-6,8-7,8-8,7-9,6-9,5-9,4-9,", AStarsearch(grid, src, dest));
 }
 @Test
 public void ExpecterPath2() throws InterruptedException {
int grid[][] =
{
{ 1, 1, 1, 1, 1, 0, 1, 1, 1, 1 },
{ 1, 1, 1, 1, 1, 0, 1, 0, 0, 1 },
{ 1, 1, 1, 1, 1, 0, 1, 0, 1, 1 },
{ 1, 1, 1, 1, 1, 0, 1, 1, 0, 1 },
{ 1, 1, 1, 1, 1, 0, 1, 1, 0, 1 },
{ 1, 1, 1, 1, 1, 0, 1, 0, 1, 1 },
{ 1, 1, 1, 1, 1, 0, 1, 1, 0, 1 },
{ 1, 1, 1, 1, 1, 0, 0, 0, 0, 1 },
{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
};
point src = new point(4,9);
point dest = new point(0,0);
 assertEquals("4-9,5-9,6-9,7-9,8-8,8-7,8-6,8-5,7-4,6-4,5-4,4-4,3-3,2-2,1-1,0-0,", AStarsearch(grid, src, dest));
 }
 @Test
 public void ExpectedInvalid1() throws InterruptedException {
int grid[][] =
{
{ 1, 1, 1, 1, 1, 0, 1, 1, 1, 1 },
{ 1, 1, 1, 1, 1, 0, 1, 0, 0, 1 },
{ 1, 1, 1, 1, 1, 0, 1, 0, 1, 1 },
{ 1, 1, 1, 1, 1, 0, 1, 1, 0, 1 },
{ 1, 1, 1, 1, 1, 0, 1, 1, 0, 1 },
{ 1, 1, 1, 1, 1, 0, 1, 0, 1, 1 },
{ 1, 1, 1, 1, 1, 0, 1, 1, 0, 1 },
{ 1, 1, 1, 1, 1, 0, 0, 0, 0, 1 },
{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
};
point src = new point(0,0);
point dest = new point(11,-1);
 assertEquals("Destination is invalid", AStarsearch(grid, src, dest));
 }
 @Test
 public void ExpectedInvalid2() throws InterruptedException {
int grid[][] =
{
{ 1, 1, 1, 1, 1, 0, 1, 1, 1, 1 },
{ 1, 1, 1, 1, 1, 0, 1, 0, 0, 1 },
{ 1, 1, 1, 1, 1, 0, 1, 0, 1, 1 },
{ 1, 1, 1, 1, 1, 0, 1, 1, 0, 1 },
{ 1, 1, 1, 1, 1, 0, 1, 1, 0, 1 },
{ 1, 1, 1, 1, 1, 0, 1, 0, 1, 1 },
{ 1, 1, 1, 1, 1, 0, 1, 1, 0, 1 },
{ 1, 1, 1, 1, 1, 0, 0, 0, 0, 1 },
{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
};
point src = new point(11,0);
point dest = new point(6,0);
 assertEquals("Source is invalid", AStarsearch(grid, src, dest));
 }
 @Test
 public void ExpectedBlocked1() throws InterruptedException {
int grid[][] =
{
{ 1, 1, 1, 1, 1, 0, 1, 1, 1, 1 },
{ 1, 1, 1, 1, 1, 0, 1, 0, 0, 1 },
{ 1, 1, 1, 1, 1, 0, 1, 0, 1, 1 },
{ 1, 1, 1, 1, 1, 0, 1, 1, 0, 1 },
{ 1, 1, 1, 1, 1, 0, 1, 1, 0, 1 },
{ 1, 1, 1, 1, 1, 0, 1, 0, 1, 1 },
{ 1, 1, 1, 1, 1, 0, 1, 1, 0, 1 },
{ 1, 1, 1, 1, 1, 0, 0, 0, 0, 1 },
{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
};
point src = new point(0,5);
point dest = new point(6,0);
 assertEquals("Source or the destination is blocked", AStarsearch(grid, src, dest));
 }

}