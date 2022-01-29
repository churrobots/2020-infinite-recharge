FROM maven:3.8-jdk-11

############################################################################################
# Update these ARGs to match this year and the correct download
# link for the Linux WPILib tarball for this year. You can find
# links to all of the WPILib tarballs here:
# https://github.com/wpilibsuite/allwpilib/releases
############################################################################################
ARG FRC_YEAR=2022
ARG WPILIB_VERSION=2022.2.1
# WARNING: you have to also change the WPILib version and year in:
# build.gradle (edu.wpi.first.GradleRIO version)
# wpilib_preferences.json (projectYear)
# WPILibNewCommands.json (version)
# settings.gradle (frcYear)

ARG WPILIB_LINUX_DOWNLOAD=https://github.com/wpilibsuite/allwpilib/releases/download/v$WPILIB_VERSION/WPILib_Linux-$WPILIB_VERSION.tar.gz
ARG PHOENIX_LINUX_DOWNLOAD=https://github.com/CrossTheRoadElec/Phoenix-Releases/releases/download/v5.20.2.2/CTRE_Phoenix_FRC_Linux_5.20.2.zip
############################################################################################

# Based on the VSCode Java development container example.
# Copyright (c) Microsoft Corporation. All rights reserved.
# Licensed under the MIT License. See LICENSE in the project root for license information.

ARG USERNAME=vscode
ARG USER_UID=1000
ARG USER_GID=$USER_UID

ENV DEBIAN_FRONTEND=noninteractive
# Workaround MacOS Big Sur disallowing http downloads
RUN sed -i 's/http:/https:/g' /etc/apt/sources.list
RUN apt-get update \
  && apt-get -y install --no-install-recommends apt-utils dialog 2>&1 \
  && apt-get -y install git procps lsb-release sudo ssh \
  && apt-get autoremove -y \
  && apt-get clean -y \
  && rm -rf /var/lib/apt/lists/*
ENV DEBIAN_FRONTEND=

RUN if [ ! -d "/docker-java-home" ]; then ln -s "${JAVA_HOME}" /docker-java-home; fi

RUN groupadd --gid $USER_GID $USERNAME
RUN useradd -s /bin/bash --uid $USER_UID --gid $USER_GID -m $USERNAME
RUN echo $USERNAME ALL=\(root\) NOPASSWD:ALL > /etc/sudoers.d/$USERNAME
RUN chmod 0440 /etc/sudoers.d/$USERNAME
USER $USERNAME

# No reinstalls of extensions so that an accidental container
# rebuild without an internet connection won't break the project.
# https://code.visualstudio.com/docs/remote/containers-advanced#_avoiding-extension-reinstalls-on-container-rebuild
RUN mkdir -p ~/.vscode-server

# Install the WPILib for this year.
# https://docs.wpilib.org/en/latest/docs/getting-started/getting-started-frc-control-system/wpilib-setup.html
RUN mkdir -p /home/$USERNAME/wpilib/$FRC_YEAR
WORKDIR /home/$USERNAME/wpilib/$FRC_YEAR
RUN wget -qO- $WPILIB_LINUX_DOWNLOAD > wpilib.tar.gz
RUN tar -xf wpilib.tar.gz
# The official installation uses a .NET based installer with a GUI, so
# instead we attempt to manually install the artifacts and hope it works.
RUN mv WPILib_Linux-$WPILIB_VERSION/WPILib_Linux-$WPILIB_VERSION-artifacts.tar.gz wpilib-artifacts.tar.gz
RUN tar -xf wpilib-artifacts.tar.gz

# Install Phoenix libraries
RUN wget -qO- $PHOENIX_LINUX_DOWNLOAD > phoenix.zip
RUN unzip -d ./phoenix phoenix.zip
RUN cp -r phoenix/maven /home/$USERNAME/wpilib/$FRC_YEAR/maven
RUN cp -r phoenix/vendordeps /home/$USERNAME/wpilib/$FRC_YEAR/vendordeps

# Make sure git works inside the container
RUN mkdir -p ~/.ssh && ssh-keyscan github.com >> ~/.ssh/known_hosts

# Warm up the gradle cache so we don't need to be online for builds.
RUN mkdir -p /tmp/repo
COPY . /tmp/repo/.
WORKDIR /tmp
RUN cp /tmp/repo/.wpilib/wpilib_preferences.json .wpilib/wpilib_preferences.json || true
RUN cp /tmp/repo/gradle gradle || true
RUN cp /tmp/repo/build.gradle build.gradle || true
RUN cp /tmp/repo/gradlew gradlew || true
RUN cp /tmp/repo/settings.gradle settings.gradle || true
RUN ./gradlew build --offline -Dorg.gradle.java.home="/docker-java-home" || true
