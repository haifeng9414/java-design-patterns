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

import java.util.Optional;

import com.iluwatar.abstractdocument.Document;

/**
 * HasModel trait for static access to 'model' property
 */
public interface HasModel extends Document {

    String PROPERTY = "model";

    //原始版本是没有这个方法的，加上这个方法的好处是设置model域时不用像DomainTest中写的
    //carProperties.put(HasModel.PROPERTY, TEST_CAR_MODEL);
    //这种形式来设置域，可以用car.setModel(TEST_CAR_MODEL)达到同样的效果。同时用setModel方法也限定了参数类型必须满足Model的类型。
    //而carProperties.put(HasModel.PROPERTY, TEST_CAR_MODEL)是没法限制的
    default Void setModel(String model) {
        return put(PROPERTY, model);
    }

    default Optional<String> getModel() {
        return Optional.ofNullable((String) get(PROPERTY));
    }

}
