/*
 * MIT License
 *
 * Copyright (c) 2017 Sijmen Huizenga
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package it.sijmen.hanasdapp.datalgos.symbolchecker;

import junit.framework.AssertionFailedError;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Sijmen on 4-3-2017.
 */

public class BalancedSymbolCheckerTest {

    BalancedSymbolChecker checker;
    BalancedSymbolCheckerV2 checkerv2;

    @Before
    public void makeChecker(){
       checker = new BalancedSymbolChecker();
       checkerv2 = new BalancedSymbolCheckerV2();
    }

    @Test
    public void testCheckBalancedSymbols() throws Exception {
        check("Sijmen{" +
                "leeftijd=(18)," +
                "geslacht=(man)," +
                "hobbies=[" +
                    "{naam=(programmeren)}, " +
                    "{naam=(filmskijken)}" +
                "]" +
            "}", true);
    }

    @Test
    public void testCheckBalancedSymbolsMissing() throws Exception {
        check("(ab", false);
    }

    @Test
    public void testCheckBalancedSymbolsMixedUp() throws Exception {
        check("(ab[)]", false);
    }

    @Test
    public void testCheckBalancedSymbolsWrong() throws Exception {
        check("(ab}", false);
    }

    @Test
    public void testCheckBalancedSymbolsBigText() throws Exception {
        check("Sijmen{" +
                "leeftijd=(18)," +
                "geslacht=(man)," +
                "hobbies=[" +
                "{naam=(programmeren)}, " +
                "naam=(filmskijken)}" +
                "]" +
                "}", false);
    }

    protected void check(String string, boolean expectedOkay){
        try{
            checker.checkBalancedSymbols(string);
            if(!expectedOkay)
                throw new AssertionFailedError("Expected illegalargumentexception but got ok");
        }catch (IllegalArgumentException e){
            if(expectedOkay)
                throw new AssertionFailedError("Expected ok but got illegalargumentexception");
        }
        Assert.assertEquals(expectedOkay, checkerv2.checkBalancedSymbols(string));
    }
}