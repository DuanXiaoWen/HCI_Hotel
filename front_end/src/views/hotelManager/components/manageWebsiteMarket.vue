<template>
    <div>
        <a-button
                style="
                margin-left: 50%;
                margin-top: 20.67%;
                transform: translate(-50%, 0);
"
                size="large"
                type="primary"
                @click="seeWebCoupon"
                v-text="'查看网站优惠'"
        />

        <Coupon/>
    </div>
</template>



<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    import Coupon from './coupon'
    export default {
        name: "manageWebsiteMarket",
        components:{
            Coupon
        },
        mounted() {
            this.set_activeHotelId(-1);
            this.set_couponVisible(true);
        },
        computed: {
            ...mapGetters([
                'activeHotelId',
                'couponVisible',
            ]),
        },
        methods: {
            ...mapMutations([
                'set_couponVisible',
                'set_activeHotelId',
            ]),
            ...mapActions([
                'getHotelCoupon',
            ]),
            seeWebCoupon(){
                this.set_couponVisible(true)
            }
        },

    }
</script>

<style scoped>

</style>