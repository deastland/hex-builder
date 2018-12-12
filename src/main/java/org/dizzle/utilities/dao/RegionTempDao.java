package org.dizzle.utilities.dao;

import org.dizzle.utilities.model.ClimaticRegion;
import org.dizzle.utilities.model.Month;
import org.dizzle.utilities.model.TempSpanChart;
import org.dizzle.utilities.model.WeatherTerrainType;

public interface RegionTempDao {

    public TempSpanChart getTempSpanChart(ClimaticRegion climaticRegion, WeatherTerrainType weatherTerrainType, Month month);
}
