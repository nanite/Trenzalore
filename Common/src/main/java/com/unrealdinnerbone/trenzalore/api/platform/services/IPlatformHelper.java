package com.unrealdinnerbone.trenzalore.api.platform.services;

import java.nio.file.Path;

public interface IPlatformHelper {

    String getPlatform();

    Path getConfigPath();

}