FROM registry.access.redhat.com/ubi9/nodejs-20-minimal:9.7-1767673633 as builder

ARG OVSX_VERSION
ENV VERSION=$OVSX_VERSION

USER root

RUN microdnf -y --nodocs --setopt=install_weak_deps=0 install \
    git \
    wget \
  && microdnf clean all

USER 1001
  
RUN npm install -g "ovsx@${OVSX_VERSION}" \
    && ovsx --version
