<template>
  <div>
    <input v-model="produktTilLagring.beskrivelse" placeholder="Beskrivelse">
    <input v-model="produktTilLagring.pris" placeholder="Pris">

    <button v-on:click="lagreprodukt" v-text="knappTekst"></button>
    <p>{{ melding }}</p>
    <li v-for="produkt in produkter" >
      <router-link :to="{name:'produkt_lagre', params:{ id: produkt.id }}">{{produkt.beskrivelse}} - pris: {{produkt.pris}}</router-link>
    </li>

  </div>
</template>

<script>
  import axios from 'axios'

  export default {

    data() {
      return {
        melding: "",
        knappTekst: 'Opprett produkt',
        produkter: [],
        produktTilLagring: {
          id: '',
          beskrivelse: '',
          pris:''
        }
      }
    },
    methods: {
      hentAlle() {
        axios.get('/api/produkt', {},
          {
            headers: {
              'Content-type': 'application/json',
            }
          }
        ).then(response => (this.produkter = response.data))

      },
      lagreprodukt() {
        if (this.produktTilLagring.beskrivelse.length == 0) {
          return;
        }
        var produktId = ''
        if (this.$route.params.id != undefined) {
          produktId = this.$route.params.id
        }

        axios
          .put('/api/produkt/' + produktId, {
              beskrivelse: this.produktTilLagring.beskrivelse,
              pris: this.produktTilLagring.pris
            }, {
              headers: {
                'Content-type': 'application/json'
              }
            }
          )
          .then(response => (
            this.melding = 'produkt er oppdatert!'
          ))
          .catch(error => {
            this.melding = "Feil. Sjekk console"
            console.log(error.response)
          });

        this.melding = 'produkt ' + this.produktbeskrivelse + ' ble opprettet'
        this.produktTilLagring.beskrivelse = ''
        this.produktTilLagring.id = ''
        this.produktTilLagring.pris = ''
        this.hentAlle()
        this.$router.push('/produkt')
      }
    },
    mounted() {
      console.log('hent alle')
      this.hentAlle()
    },
    watch: {
      '$route.params.id'(newId, oldId) {
        this.melding = ''
        if (this.$route.params.id == undefined) {
          'Opprett produkt'
          return
        }

        this.knappTekst = 'Lagre produkt'

        axios.get('/api/produkt/' + this.$route.params.id, {},
          {
            headers: {
              'Content-type': 'application/json',
            }
          }
        ).then(response => (this.produktTilLagring = response.data))

      },
      '$route' (to, from) {
        this.hentAlle()
      }
    }
  }
</script>