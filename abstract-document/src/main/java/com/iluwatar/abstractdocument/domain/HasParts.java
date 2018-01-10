/**
 * The MIT License
 * Copyright (c) 2014-2016 Ilkka Seppälä
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.iluwatar.abstractdocument.domain;

import java.util.stream.Stream;

import com.iluwatar.abstractdocument.Document;

/**
 * HasParts trait for static access to 'parts' property
 */
public interface HasParts extends Document {

    String PROPERTY = "parts";

    default Stream<Part> getParts() {
        //children方法的第二个参数是Function<Map<String, Object>, T> constructor，该参数表示需要一个Function，该Function能够从
        //Map<String, Object>类型的对象中返回T类型的对象，而getParts方法中T为Part，所以需要一个Function能够将Map<String, Object>
        //转化为Part，而Part的构造方法就可以完成这个功能，::符号获取方法的引用，返回一个Function。
        return children(PROPERTY, Part::new);
    }

}
