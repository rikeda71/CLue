FROM frolvlad/alpine-python-machinelearning

WORKDIR /local
ADD requirements.txt /local/requirements.txt

RUN apk --update --no-cache add \
    libressl-dev libstdc++ openblas lapack-dev libexecinfo-dev libgomp git \
    && apk --update --no-cache --virtual .builddeps add \
    build-base curl gcc g++ swig musl-dev python3-dev libffi-dev libexecinfo-dev cmake ca-certificates\
    && git clone https://github.com/taku910/mecab.git \
    && cd mecab/mecab \
    && ./configure --enable-utf8-only \
    && make \
    && make check \
    && make install \
    && cd ../mecab-ipadic \
    && ./configure --with-charset=utf8 \
    && make \
    && make install \
    && cd /local \
    && pip install -U pip \
    && pip install cython \
    && pip install  --no-cache-dir -r requirements.txt \
    && git clone --recursive -b v0.81 https://github.com/dmlc/xgboost \
    && sed -i '/#define DMLC_LOG_STACK_TRACE 1/d' /local/xgboost/dmlc-core/include/dmlc/base.h \
    && sed -i '/#define DMLC_LOG_STACK_TRACE 1/d' /local/xgboost/rabit/include/dmlc/base.h \
    && ln -s locale.h /usr/include/xlocale.h \
    && cd /local/xgboost; make -j4 \
    && cd /local/xgboost/python-package \
    && python3 setup.py install \
    && rm /usr/include/xlocale.h \
    && rm -rf /root/.cache \
    && rm -rf /local/* \
    && apk del --purge .builddeps

WORKDIR /app
ADD .env /app/.env