<template>
  <div>
    <input v-model="kundeTilLagring.navn" placeholder="Kundenavn">
    <button v-if="kundeTilLagring.id == ''" v-on:click.stop.prevent="opprettKunde">Opprett kunde</button>
    <button v-if="kundeTilLagring.id != ''" v-on:click.stop.prevent="lagreKunde">Lagre kunde</button>
    <p>{{ melding }}</p>
    <li v-for="kunde in kundee">
      <button v-on:click="slettKunde(kunde.id)">Slett</button>
      <router-link :to="{name:'kunde_lagre', params:{ id: kunde.id }}">{{kunde.navn}}</router-link>
    </li>

  </div>
</template>

<script>
  import axios from 'axios'

  export default {

    data() {
      return {
        melding: "",
        kundee: [],
        kundeTilLagring: {
          id: '',
          navn: ''
        }
      }
    },
    methods: {
      tømFelter(){
        this.kundeTilLagring.id = ''
        this.kundeTilLagring.navn= ''
      },
      lagreKunde() {
        axios
          .put('/api/kunde/' + this.$route.params.id, {
              navn: this.kundeTilLagring.navn
            }, {
              headers: {
                'Content-type': 'application/json'
              }
            }
          )
          .then(response => {
            this.melding = 'Kunde er oppdatert!'
            this.hentAlle()
            this.tømFelter()
            this.$router.push('/kunde')
          })
          .catch(error => {
            this.melding = "Feil. Sjekk console"
            console.log(error.response)
          });
      },
      opprettKunde() {

        axios
          .put('/api/kunde/', {
              navn: this.kundeTilLagring.navn
            }, {
              headers: {
                'Content-type': 'application/json'
              }
            }
          )
          .then(response => {
            this.melding = 'Kunde er oppdatert!'
            this.hentAlle()
            this.tømFelter()
          })
          .catch(error => {
            this.melding = "Feil. Sjekk console"
            console.log(error.response)
          });
      },
      slettKunde(id) {
        axios.delete('/api/kunde/' + id, {},
          {}
        ).then(response => {
          this.kundee = response.data
          this.hentAlle()
        })
      },
      hentAlle() {
        axios.get('/api/kunde', {},
          {
            headers: {
              'Content-type': 'application/json',
            }
          }
        ).then(response => (this.kundee = response.data))
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
          'Opprett kunde'
          return
        }

        var kundeId = this.$route.params.id

        axios.get('/api/kunde/' + kundeId, {},
          {
            headers: {
              'Content-type': 'application/json',
            }
          }
        ).then(response => (this.kundeTilLagring = response.data))

      }
    }
  }
</script>