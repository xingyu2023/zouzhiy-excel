/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.zouzhiy.excel.old.example.example1;

import io.github.zouzhiy.excel.old.example.example1.matedata.Demo;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author zouzhiy
 * @since 2022/7/4
 */
class ExcelTest {

    private final Random random = new Random(System.currentTimeMillis());

    @Test
    void write1() {
        Excel excel = new Excel();
        excel.write(Collections.emptyList());

        List<Demo> demoList = new ArrayList<>();
        int maxIndex = random.nextInt(500);
        for (int i = 0; i < maxIndex; i++) {
            demoList.add(Demo.builder().value(this.getValue())
                    .value1List(this.getStringList())
                    .value2List(this.getStringList())
                    .value3List(this.getStringList())
                    .build());
        }
        File outputFile = excel.write(demoList);

        List<Demo> demo1List = excel.read(outputFile);

        assert demo1List.equals(demoList);

    }

    @Test
    void write2() {
        Excel excel = new Excel();
        List<Demo> demoList = new ArrayList<>();
        demoList.add(Demo.builder().value(this.getValue())
                .value1List(new ArrayList<>())
                .value2List(Collections.singletonList(""))
                .value3List(Collections.singletonList(null))
                .value4List(null)
                .build());
        File outputFile = excel.write(demoList);

        List<Demo> demo1List = excel.read(outputFile);

        assert demo1List.equals(demoList);
    }

    private String getValue() {
        return "test" + random.nextInt(440);
    }

    private List<String> getStringList() {
        int maxIndex = random.nextInt(10);
        List<String> valueList = new ArrayList<>();
        for (int i = 0; i < maxIndex; i++) {
            valueList.add("test-" + maxIndex + "-" + i);
        }
        return valueList;
    }
}
