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

 @Before
 public void setUp() {
 Frame frame = GuiActionRunner.execute(() -> new Frame());
 window = new FrameFixture(frame);
 window.show(); // shows the frame to test
 }
 @Test
 public void ExpectFillAllBoxesAndGetResoult() throws InterruptedException {

 window.textBox("i1").enterText("0");
 window.textBox("i2").enterText("0");
 window.textBox("i3").enterText("2");
 window.textBox("i4").enterText("2");
 window.button("Enter").click();
 window.textBox("i5").requireText("0-0,1-1,2-2,");
 }
 @Test
 public void ExpectFillAllBoxesAndGetInvalid() throws InterruptedException {

 window.textBox("i1").enterText("-1");
 window.textBox("i2").enterText("0");
 window.textBox("i3").enterText("2");
 window.textBox("i4").enterText("2");
 window.button("Enter").click();
 window.textBox("i5").requireText("Source is invalid");
 }
 @Test
 public void ExpectFillAllBoxesAndGetInvalidDestin() throws InterruptedException {
 window.textBox("i1").enterText("1");
 window.textBox("i2").enterText("0");
 window.textBox("i3").enterText("-2");
 window.textBox("i4").enterText("2");
 window.button("Enter").click();
 window.textBox("i5").requireText("Destination is invalid");
 }
 @After
 public void tearDown() {
 window.cleanUp();
 }
}