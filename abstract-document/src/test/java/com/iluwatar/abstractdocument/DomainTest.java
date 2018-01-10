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
package com.iluwatar.abstractdocument;

import com.iluwatar.abstractdocument.domain.Car;
import com.iluwatar.abstractdocument.domain.HasModel;
import com.iluwatar.abstractdocument.domain.HasParts;
import com.iluwatar.abstractdocument.domain.HasPrice;
import com.iluwatar.abstractdocument.domain.HasType;
import com.iluwatar.abstractdocument.domain.Part;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for Part and Car
 */
public class DomainTest {

    private static final String TEST_PART_TYPE = "test-part-type";
    private static final String TEST_PART_MODEL = "test-part-model";
    private static final long TEST_PART_PRICE = 0L;

    private static final String TEST_CAR_MODEL = "test-car-model";
    private static final long TEST_CAR_PRICE = 1L;

    @Test
    public void shouldConstructPart() {
        Map<String, Object> partProperties = new HashMap<>();
        partProperties.put(HasType.PROPERTY, TEST_PART_TYPE);
        partProperties.put(HasModel.PROPERTY, TEST_PART_MODEL);
        partProperties.put(HasPrice.PROPERTY, TEST_PART_PRICE);
        Part part = new Part(partProperties);

        assertEquals(TEST_PART_TYPE, part.getType().get());
        assertEquals(TEST_PART_MODEL, part.getModel().get());
        assertEquals(TEST_PART_PRICE, part.getPrice().get());
    }

    @Test
    public void shouldConstructCar() {
        //初始化Car对象的Part域的第一个Part
        Map<String, Object> partProperties1 = new HashMap<>();
        partProperties1.put(HasType.PROPERTY, TEST_PART_TYPE);
        partProperties1.put(HasModel.PROPERTY, TEST_PART_MODEL);
        partProperties1.put(HasPrice.PROPERTY, TEST_PART_PRICE);

        //初始化Car对象的Part域的第二个Part
        Map<String, Object> partProperties2 = new HashMap<>();
        partProperties2.put(HasType.PROPERTY, TEST_PART_TYPE);
        partProperties2.put(HasModel.PROPERTY, TEST_PART_MODEL);
        partProperties2.put(HasPrice.PROPERTY, TEST_PART_PRICE);

        Map<String, Object> carProperties = new HashMap<>();
        carProperties.put(HasModel.PROPERTY, TEST_CAR_MODEL);
        carProperties.put(HasPrice.PROPERTY, TEST_CAR_PRICE);
        //这里以元素为Map的List为值，这么传是因为AbstractDocument中children的实现是假设AbstractDocument实现类的任意一个child
        //是一个list，list的元素是Map<String, Object>，该map用于组成AbstractDocument实现类的child实例，如Car拥有Part域，则Car的
        //map中有key为parts，值为List<Map<String, Object>>的键值对，list中的元素map即是Car对象的所有Part的域，当获取Car对象的parts
        //时，首先获取Car的map中parts对应的List<Map<String, Object>>，再使用Steam对象的map()方法对返回的list中的所有元素做一次
        //Function<Map<String, Object>, T> constructor操作，即HasParts接口中的Part::new操作，这样就从返回的list获取了Steam<Part>
        //即Part流
        carProperties.put(HasParts.PROPERTY, Arrays.asList(partProperties1, partProperties2));
        Car car = new Car(carProperties);

        assertEquals(TEST_CAR_MODEL, car.getModel().get());
        assertEquals(TEST_CAR_PRICE, car.getPrice().get());
        assertEquals(TEST_PART_TYPE, car.getParts().findFirst().get().getType().get());
        assertEquals(2, car.getParts().count());
    }

}
