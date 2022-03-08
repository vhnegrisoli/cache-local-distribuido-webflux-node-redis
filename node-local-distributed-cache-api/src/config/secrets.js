const env = process.env;

export const VIA_CEP_URI = (cep) => {
  return `https://viacep.com.br/ws/${cep}/json/`;
};
export const TTL = 60;
export const CACHE_SCHEDULER = "0 * * * * *";
export const REDIS_ENABLED = env.REDIS_ENABLED ? env.REDIS_ENABLED : true;
export const REDIS_URL = env.REDIS_URL ? env.REDIS_URL : "localhost:6379";
