# ROM Info App - System prebuilt
# Add this line in your vendor/<romname>/config/common.mk or similar:
#
#   $(call inherit-product-if-exists, vendor/<romname>/prebuilt/ROMInfo/rominfo.mk)
#

PRODUCT_PACKAGES += \
    ROMInfo
