<template>
  <div>
    <input v-model="selgerTilLagring.navn" placeholder="Selgernavn">
    <button v-if="selgerTilLagring.id == ''" v-on:click.stop.prevent="opprettSelger">Opprett selger</button>
    <button v-if="selgerTilLagring.id != ''" v-on:click.stop.prevent="lagreSelger">Lagre selger</button>
    <p>{{ melding }}</p>
    <li v-for="selger in selgere">
      <button v-on:click="slettSelger(selger.id)">Slett</button>
      <router-link :to="{name:'selger_lagre', params:{ id: selger.id }}">{{selger.navn}}</router-link>
    </li>

  </div>
</template>

<script>
  import axios from 'axios'

  export default {

    data() {
      return {
        melding: "",
        selgere: [],
        selgerTilLagring: {
          id: '',
          navn: ''
        }
      }
    },
    methods: {
      tømFelter(){
        this.selgerTilLagring.id = ''
        this.selgerTilLagring.navn= ''
      },
      lagreSelger() {
        axios
          .put('/api/ansatt/' + this.$route.params.id, {
              navn: this.selgerTilLagring.navn
            }, {
              headers: {
                'Content-type': 'application/json'
              }
            }
          )
          .then(response => {
            this.melding = 'Selger er oppdatert!'
            this.hentAlle()
            this.tømFelter()
            this.$router.push('/selger')
          })
          .catch(error => {
            this.melding = "Feil. Sjekk console"
            console.log(error.response)
          });
      },
      opprettSelger() {

        axios
          .put('/api/ansatt/', {
              navn: this.selgerTilLagring.navn
            }, {
              headers: {
                'Content-type': 'application/json'
              }
            }
          )
          .then(response => {
            this.melding = 'Selger er oppdatert!'
            this.hentAlle()
            this.tømFelter()
          })
          .catch(error => {
            this.melding = "Feil. Sjekk console"
            console.log(error.response)
          });
      },
      slettSelger(id) {
        axios.delete('/api/ansatt/' + id, {},
          {}
        ).then(response => {
          this.selgere = response.data
          this.hentAlle()
        })
      },
      hentAlle() {
        axios.get('/api/ansatt', {},
          {
            headers: {
              'Content-type': 'application/json',
            }
          }
        ).then(response => (this.selgere = response.data))
          .catch(error => {
            this.melding = "Feil. Sjekk console"
            console.log(error.response)
          });

      },
    },

    mounted() {
      console.log('hent alle')
      this.hentAlle()
    },
    watch: {
      '$route.params.id'(newId, oldId) {
        this.melding = ''
        if (this.$route.params.id == undefined) {
          'Opprett selger'
          return
        }

        var selgerId = this.$route.params.id

        axios.get('/api/ansatt/' + selgerId, {},
          {
            headers: {
              'Content-type': 'application/json',
            }
          }
        ).then(response => (this.selgerTilLagring = response.data))

      }
    }
  }
</script>