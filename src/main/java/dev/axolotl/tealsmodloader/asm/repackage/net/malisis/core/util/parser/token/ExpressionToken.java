/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Ordinastie
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package dev.axolotl.tealsmodloader.asm.repackage.net.malisis.core.util.parser.token;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ordinastie
 *
 */
public class ExpressionToken extends Token<String>
{
    private Pattern pattern;

    public ExpressionToken(String regex)
    {
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public boolean matches(String s, int index)
    {
        value = null;
        Matcher m = pattern.matcher(s.substring(index));
        if (!m.find())
            return false;

        value = m.group(0);
        return true;
    }

    @Override
    public int size()
    {
        return value != null ? value.length() : 0;
    }

}
