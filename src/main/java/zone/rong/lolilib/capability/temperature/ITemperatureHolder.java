package zone.rong.lolilib.capability.temperature;

public interface ITemperatureHolder {

    default float getAmbientTemperature() {
        return 37F;
    }

    float getCurrentTemperature();

}
