FROM postgres:15

ENV POSTGRES_USER=jwt_security
ENV POSTGRES_PASSWORD=1234

COPY postgresql.conf /etc/postgresql/postgresql.conf

EXPOSE 5432

CMD ["postgres", "-c", "config_file=/etc/postgresql/postgresql.conf"]