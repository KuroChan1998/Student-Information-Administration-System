package com.jzy.dto.other.senior;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JinZhiyun
 * @ClassName EchartsFactory
 * @Description 处理Echarts所需传输对象类的工厂类
 * @Date 2019/7/23 21:21
 * @Version 1.0
 **/
public class EchartsFactory implements Serializable {
    private static final long serialVersionUID = -8277175130803043490L;

    private static class Series {
        private String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Series(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "Series{" +
                    "type='" + type + '\'' +
                    '}';
        }
    }

    private static class SeriesInFirstGrid extends Series {
        private String seriesLayoutBy;

        public String getSeriesLayoutBy() {
            return seriesLayoutBy;
        }

        public void setSeriesLayoutBy(String seriesLayoutBy) {
            this.seriesLayoutBy = seriesLayoutBy;
        }

        public SeriesInFirstGrid(String type, String seriesLayoutBy) {
            super(type);
            this.seriesLayoutBy = seriesLayoutBy;
        }

        @Override
        public String toString() {
            return "SeriesInFirstGrid{" +
                    "seriesLayoutBy='" + seriesLayoutBy + '\'' +
                    "} " + super.toString();
        }
    }

    private static class SeriesInSecondGrid extends Series {
        private Integer xAxisIndex;

        private Integer yAxisIndex;

        public Integer getxAxisIndex() {
            return xAxisIndex;
        }

        public void setxAxisIndex(Integer xAxisIndex) {
            this.xAxisIndex = xAxisIndex;
        }

        public Integer getyAxisIndex() {
            return yAxisIndex;
        }

        public void setyAxisIndex(Integer yAxisIndex) {
            this.yAxisIndex = yAxisIndex;
        }

        public SeriesInSecondGrid(String type, Integer xAxisIndex, Integer yAxisIndex) {
            super(type);
            this.xAxisIndex = xAxisIndex;
            this.yAxisIndex = yAxisIndex;
        }

        @Override
        public String toString() {
            return "SeriesInSecondGrid{" +
                    "xAxisIndex=" + xAxisIndex +
                    ", yAxisIndex=" + yAxisIndex +
                    "} " + super.toString();
        }
    }

    public static SeriesInFirstGrid getSeriesInFirstGrid() {
        return new SeriesInFirstGrid("bar", "row");
    }

    public static List<SeriesInFirstGrid> getSeriesInFirstGrids(int howMany) {
        List<SeriesInFirstGrid> seriesInFirstGrids = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            seriesInFirstGrids.add(new SeriesInFirstGrid("bar", "row"));
        }
        return seriesInFirstGrids;
    }

    public static SeriesInSecondGrid getSeriesInSecondGrid() {
        return new SeriesInSecondGrid("bar", 1, 1);
    }

    public static List<SeriesInSecondGrid> getSeriesInSecondGrids(int howMany) {
        List<SeriesInSecondGrid> seriesInSecondGrids = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            seriesInSecondGrids.add(new SeriesInSecondGrid("bar", 1, 1));
        }
        return seriesInSecondGrids;
    }
}
